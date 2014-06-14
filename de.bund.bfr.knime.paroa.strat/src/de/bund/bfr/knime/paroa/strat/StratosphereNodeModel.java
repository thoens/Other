package de.bund.bfr.knime.paroa.strat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.knime.core.data.DataCell;
import org.knime.core.data.DataColumnSpec;
import org.knime.core.data.DataColumnSpecCreator;
import org.knime.core.data.DataRow;
import org.knime.core.data.DataTableSpec;
import org.knime.core.data.RowKey;
import org.knime.core.data.def.DefaultRow;
import org.knime.core.data.def.DoubleCell;
import org.knime.core.data.def.IntCell;
import org.knime.core.data.def.StringCell;
import org.knime.core.node.BufferedDataContainer;
import org.knime.core.node.BufferedDataTable;
import org.knime.core.node.CanceledExecutionException;
import org.knime.core.node.ExecutionContext;
import org.knime.core.node.ExecutionMonitor;
import org.knime.core.node.InvalidSettingsException;
import org.knime.core.node.NodeLogger;
import org.knime.core.node.NodeModel;
import org.knime.core.node.NodeSettingsRO;
import org.knime.core.node.NodeSettingsWO;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelInteger;
import org.knime.core.node.defaultnodesettings.SettingsModelString;
import org.knime.core.node.port.PortObject;
import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortType;
import org.knime.core.node.port.flowvariable.FlowVariablePortObject;

/**
 * This is the model implementation of the Stratosphere-Node.
 * 
 * 
 * @author Markus Freitag
 */
public class StratosphereNodeModel extends NodeModel {

    // the logger instance
    private static final NodeLogger logger = NodeLogger
	    .getLogger(StratosphereNodeModel.class);

    static final String CFGKEY_METHODS = "methods";
    static final String CFGKEY_EXEC = "execution environment";
    static final String CFGKEY_SCENARIOS = "scenarios";
    static final String CFGKEY_JAR = "Stratosphere jar";
    static final String CFGKEY_LOCAL = "local";
    static final String CFGKEY_INPUT_SALES = "Sales Data Input";
    static final String CFGKEY_INPUT_OUTBREAKS = "Outbreak Data Input";
    static final String CFGKEY_INPUT_COORDINATES = "Coordinates Data Input";
    static final String CFGKEY_STRAT_PATH = "Stratosphere Path";
    static final String CFGKEY_STRAT_CONF = "Stratosphere Configuration";

    static final String STRAT_OUTBREAKS = "/data/outbreaks/";
    static final String STRAT_SALES = "/data/sales/";
    static final String STRAT_RESULTS = "data/results/";

    static final String MSG_READING_DATA = "Reading paths and settings.";
    static final String MSG_CONFIG_STRATO = "Configuring Stratosphere call.";
    static final String MSG_STRATO_CON = "Establishing Stratosphere connection.";
    static final String MSG_RUN_STRATO = "Executing Stratosphere operations.";
    static final String MSG_STRATO_CALL = "Stratosphere call arguments: ";
    static final String MSG_STRATO_FIN = "Stratosphere execution successful.";
    static final String MSG_STRATO_ERROR = "Stratosphere execution could not be completed sucessfully.";

    enum METHODS {
	SYRJALA, LBM
    };

    enum EXEC {
	LOCAL, REMOTE
    };

    enum INPUTS {
	SALES, OUTBREAKS
    }; // order matters!

    static final String DEFAULT_STRAT_PATH = "/opt/stratosphere/";
    static final String DEFAULT_METHOD = METHODS.LBM.name();
    static final Boolean DEFAULT_EXEC = true;
    static final String DEFAULT_EMPTYSTRING = "";
    static final String DEFAULT_NULL = null;
    static final int DEFAULT_SCENARIOS = 1;

    public static final String[] METHOD_CHIOCES = { METHODS.SYRJALA.name(),
	    METHODS.LBM.name() };
    public static final String[] LOCAL = { "LOCAL", "CLUSTER" };

    private final SettingsModelString m_methods = new SettingsModelString(
	    StratosphereNodeModel.CFGKEY_METHODS,
	    StratosphereNodeModel.DEFAULT_METHOD);

    private final SettingsModelBoolean m_exec = new SettingsModelBoolean(
	    StratosphereNodeModel.CFGKEY_EXEC,
	    StratosphereNodeModel.DEFAULT_EXEC);

    private final SettingsModelString m_jar = new SettingsModelString(
	    StratosphereNodeModel.CFGKEY_JAR,
	    StratosphereNodeModel.DEFAULT_NULL);

    private final SettingsModelString m_inputSales = new SettingsModelString(
	    StratosphereNodeModel.CFGKEY_INPUT_SALES,
	    StratosphereNodeModel.DEFAULT_NULL);

    private final SettingsModelString m_inputOutbreaks = new SettingsModelString(
	    StratosphereNodeModel.CFGKEY_INPUT_OUTBREAKS,
	    StratosphereNodeModel.DEFAULT_NULL);

    private final SettingsModelString m_stratospherePath = new SettingsModelString(
	    StratosphereNodeModel.CFGKEY_STRAT_PATH,
	    StratosphereNodeModel.DEFAULT_STRAT_PATH);

    private final SettingsModelString m_stratosphereConfiguration = new SettingsModelString(
	    StratosphereNodeModel.CFGKEY_STRAT_CONF,
	    StratosphereNodeModel.DEFAULT_NULL);

    private final SettingsModelString m_inputCoordinates = new SettingsModelString(
	    StratosphereNodeModel.CFGKEY_INPUT_COORDINATES,
	    StratosphereNodeModel.DEFAULT_NULL);

    private final SettingsModelInteger m_scenarios = new SettingsModelInteger(
	    StratosphereNodeModel.CFGKEY_SCENARIOS,
	    StratosphereNodeModel.DEFAULT_SCENARIOS);

    /**
     * Constructor for the node model.
     */
    protected StratosphereNodeModel() {

	super(new PortType[] { FlowVariablePortObject.TYPE,
		FlowVariablePortObject.TYPE }, new PortType[] {
		BufferedDataTable.TYPE, BufferedDataTable.TYPE });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected BufferedDataTable[] execute(final PortObject[] inData,
	    final ExecutionContext exec) throws Exception {
	exec.setProgress(0.01, "Initializing...");
	logger.info(MSG_READING_DATA);

	int numScenarios = m_scenarios.getIntValue();
//	String methodsChoice = m_methods.getStringValue();
	boolean execChoice = m_exec.getBooleanValue();
	String jarChoice = m_jar.getStringValue();
	String inputSalesChoice = m_inputSales.getStringValue();
	String inputOutbreaksChoice = m_inputOutbreaks.getStringValue();
	String inputCoordinatesChoice = m_inputCoordinates.getStringValue();
	String stratospherePathChoice = m_stratospherePath.getStringValue();

	// TODO: more
	checkPath(m_inputSales);
	
	logger.info(MSG_CONFIG_STRATO);
	StratosphereConnection paroa_connection = new StratosphereConnection(
		stratospherePathChoice, jarChoice, execChoice, exec);
//	FileHandle paroa_output = new FileHandle(stratospherePathChoice);
	FileHandle paroa_output = new FileHandle("C:/Users/mfreitag/Dropbox/MasterThesis/data/results/");

	int numCases = getNumCases(inputOutbreaksChoice);
	int numProducts = getNumProducts(inputSalesChoice);

	logger.info(MSG_STRATO_CON);
	// here happens the action
	// TODO EXEC
	paroa_connection.runParoa(EXEC.REMOTE, inputSalesChoice, numProducts,
		inputOutbreaksChoice, numCases, stratospherePathChoice, numScenarios,
		inputCoordinatesChoice);
	DataColumnSpec[] lbmColSpecs = new DataColumnSpec[2];
	lbmColSpecs[0] = new DataColumnSpecCreator("Product", IntCell.TYPE)
		.createSpec();
	lbmColSpecs[1] = new DataColumnSpecCreator("Value", DoubleCell.TYPE)
		.createSpec();
	DataTableSpec lbmSpec = new DataTableSpec(lbmColSpecs);

	DataColumnSpec[] ranksColSpecs = new DataColumnSpec[2];
	ranksColSpecs[0] = new DataColumnSpecCreator("Scenario",
		StringCell.TYPE).createSpec();
	ranksColSpecs[1] = new DataColumnSpecCreator("Rank", IntCell.TYPE)
		.createSpec();
	DataTableSpec ranksSpec = new DataTableSpec(ranksColSpecs);

	// reading results file
	File ranksFile = new File(paroa_output.getPath()
		+ "ranks_"
		+ generateOutputFileName(inputOutbreaksChoice,
			inputSalesChoice, numScenarios, false)	);
	File lbmFile = new File(
		paroa_output.getPath()
			+ "lbm_"
			+ generateOutputFileName(inputOutbreaksChoice,
				inputSalesChoice, false));

	FileInputStream ranksStream = new FileInputStream(ranksFile);
	InputStreamReader ranksReader = new InputStreamReader(ranksStream);
	BufferedReader ranksBufferedReader = new BufferedReader(ranksReader);

	FileInputStream lbmStream = new FileInputStream(lbmFile);
	InputStreamReader lbmReader = new InputStreamReader(lbmStream);
	BufferedReader lbmBufferedReader = new BufferedReader(lbmReader);

	BufferedDataContainer ranksContainer = exec
		.createDataContainer(ranksSpec);
	BufferedDataContainer lbmContainer = exec.createDataContainer(lbmSpec);

	String ranks_currentLine = ranksBufferedReader.readLine();
	while (ranks_currentLine != null) {
	    String[] lineValues = ranks_currentLine.split(";");

	    RowKey key = new RowKey(lineValues[0]);
	    DataCell[] cells = new DataCell[2];
	    cells[0] = new StringCell(lineValues[0]);
	    cells[1] = new IntCell(Integer.parseInt(lineValues[1]));

	    DataRow row = new DefaultRow(key, cells);
	    ranksContainer.addRowToTable(row);
	    exec.checkCanceled();
	    ranks_currentLine = ranksBufferedReader.readLine();
	}

	ranksContainer.close();

	String lbm_currentLine = lbmBufferedReader.readLine();
	while (lbm_currentLine != null) {
	    String[] lineValues = lbm_currentLine.split(";");

	    RowKey key = new RowKey(lineValues[0]);
	    DataCell[] cells = new DataCell[2];
	    cells[0] = new IntCell(Integer.parseInt(lineValues[0]));
	    cells[1] = new DoubleCell(Double.parseDouble(lineValues[1]));

	    DataRow row = new DefaultRow(key, cells);
	    lbmContainer.addRowToTable(row);
	    exec.checkCanceled();
	    lbm_currentLine = lbmBufferedReader.readLine();
	}

	lbmContainer.close();
	exec.setProgress(0.99);

	BufferedDataTable ranksTable = ranksContainer.getTable();
	BufferedDataTable lbmTable = lbmContainer.getTable();

	BufferedDataTable[] output = new BufferedDataTable[2];
	output[0] = ranksTable;
	output[1] = lbmTable;

	return output;
    }

    private int getNumCases(String inputOutbreaksChoice) throws Exception {
	// read outbreaks
	final File outbreaks = new File(inputOutbreaksChoice.replace("file:/",
		""));
	final FileReader out_reader = new FileReader(outbreaks);
	final BufferedReader b_reader = new BufferedReader(out_reader);
	int sumCases = 0;
	String currentLineO;
	currentLineO = b_reader.readLine();
	while (currentLineO != null) {
	    sumCases += Integer.parseInt(currentLineO.split(",")[1]);
	    currentLineO = b_reader.readLine();
	}
	b_reader.close();

	return sumCases;
    }

    private int getNumProducts(String inputSalesChoice) throws Exception {
	// read sales
	final File sales = new File(inputSalesChoice.replace("file:/", ""));
	final FileReader sales_reader = new FileReader(sales);
	final BufferedReader sales_b_reader = new BufferedReader(sales_reader);
	int sumProducts;
	String currentLineSales;
	currentLineSales = sales_b_reader.readLine();
	sumProducts = currentLineSales.split("\t").length - 1;
	sales_b_reader.close();

	return sumProducts;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void reset() {
	// TODO Code executed on reset.
	// Models build during execute are cleared here.
	// Also data handled in load/saveInternals will be erased here.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PortObjectSpec[] configure(final PortObjectSpec[] inSpecs)
	    throws InvalidSettingsException {

	// TODO: check if user settings are available, fit to the incoming
	// table structure, and the incoming types are feasible for the node
	// to execute. If the node can execute in its current state return
	// the spec of its output data table(s) (if you can, otherwise an array
	// with null elements), or throw an exception with a useful user message

	return new PortObjectSpec[] { null, null };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveSettingsTo(final NodeSettingsWO settings) {

	m_methods.saveSettingsTo(settings);
	m_exec.saveSettingsTo(settings);
	m_stratospherePath.saveSettingsTo(settings);
	m_stratosphereConfiguration.saveSettingsTo(settings);
	m_jar.saveSettingsTo(settings);
	m_inputOutbreaks.saveSettingsTo(settings);
	m_inputSales.saveSettingsTo(settings);
	m_inputCoordinates.saveSettingsTo(settings);
	m_scenarios.saveSettingsTo(settings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadValidatedSettingsFrom(final NodeSettingsRO settings)
	    throws InvalidSettingsException {

	m_methods.loadSettingsFrom(settings);
	m_exec.loadSettingsFrom(settings);
	m_stratospherePath.loadSettingsFrom(settings);
	m_stratosphereConfiguration.loadSettingsFrom(settings);
	m_jar.loadSettingsFrom(settings);
	m_inputSales.loadSettingsFrom(settings);
	m_inputOutbreaks.loadSettingsFrom(settings);
	m_inputCoordinates.loadSettingsFrom(settings);
	m_scenarios.loadSettingsFrom(settings);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void validateSettings(final NodeSettingsRO settings)
	    throws InvalidSettingsException {

	m_methods.validateSettings(settings);
	m_exec.validateSettings(settings);
	m_stratospherePath.validateSettings(settings);
	m_stratosphereConfiguration.validateSettings(settings);
	m_jar.validateSettings(settings);
	m_inputOutbreaks.validateSettings(settings);
	m_inputSales.validateSettings(settings);
	m_inputCoordinates.validateSettings(settings);
	m_scenarios.validateSettings(settings);

    }

    private void checkPath(SettingsModelString filePath) throws InvalidSettingsException {
	String path = filePath.getStringValue();

	if (path == null) {
	    throw new InvalidSettingsException("no file given");
	}
	File testFile = new File(path);
	if (!testFile.exists())
	    throw new InvalidSettingsException(CFGKEY_INPUT_SALES);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void loadInternals(final File internDir,
	    final ExecutionMonitor exec) throws IOException,
	    CanceledExecutionException {

	// TODO load internal data.
	// Everything handed to output ports is loaded automatically (data
	// returned by the execute method, models loaded in loadModelContent,
	// and user settings set through loadSettingsFrom - is all taken care
	// of). Load here only the other internals that need to be restored
	// (e.g. data used by the views).

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void saveInternals(final File internDir,
	    final ExecutionMonitor exec) throws IOException,
	    CanceledExecutionException {

	// TODO save internal models.
	// Everything written to output ports is saved automatically (data
	// returned by the execute method, models saved in the saveModelContent,
	// and user settings saved through saveSettingsTo - is all taken care
	// of). Save here only the other internals that need to be preserved
	// (e.g. data used by the views).

    }

    private static String generateOutputFileName(String outbreaksFile, String salesFile, Boolean withGS) {
	String gs = withGS ? "_mitGS_" : "_ohneGS_";
	final String outputFile = extractFileName(outbreaksFile) + "_ON_" + extractFileName(salesFile) + gs + ".txt";
	return outputFile;
    }

    private static String generateOutputFileName(String outbreaksFile, String salesFile, Integer numScenarios,
	    Boolean withGS) {
	String gs = withGS ? "_mitGS_" : "_ohneGS_";
	final String outputFile = extractFileName(outbreaksFile) + "_ON_" + extractFileName(salesFile) + "_MTS_"
		+ numScenarios + gs + ".txt";
	return outputFile;
    }


    private static String extractFileName(String path) {
	int numSubstrings;
	String file;
	if (path.contains("\\")) {
	    numSubstrings = path.split("\\").length;
	    file = path.split("\\")[numSubstrings - 1];
	} else {
	    numSubstrings = path.split("/").length;
	    file = path.split("/")[numSubstrings - 1];
	}
	String fileBlank = file.split("\\.")[0];
	return fileBlank;
    }
}
