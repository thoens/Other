package de.bund.bfr.knime.aaw.lims;

import org.knime.core.node.defaultnodesettings.DefaultNodeSettingsPane;
import org.knime.core.node.defaultnodesettings.DialogComponentBoolean;
import org.knime.core.node.defaultnodesettings.DialogComponentString;
import org.knime.core.node.defaultnodesettings.SettingsModelBoolean;
import org.knime.core.node.defaultnodesettings.SettingsModelString;

/**
 * <code>NodeDialog</code> for the "MyJavaJoiner" Node.
 * 
 *
 * This node dialog derives from {@link DefaultNodeSettingsPane} which allows
 * creation of a simple dialog with standard components. If you need a more 
 * complex dialog please derive directly from 
 * {@link org.knime.core.node.NodeDialogPane}.
 * 
 * @author BfR
 */
public class MyJavaMatcherNodeDialog extends DefaultNodeSettingsPane {

    /**
     * New pane for configuring the MyJavaJoiner node.
     */
	protected MyJavaMatcherNodeDialog() {
    	createNewGroup("BVL:"); 
    	SettingsModelString sms = new SettingsModelString(MyJavaMatcherNodeModel.BVL_PROBENNR, "");
    	SettingsModelString tpn = new SettingsModelString(MyJavaMatcherNodeModel.BVL_TEILPROBENNR, "");
    	SettingsModelString bvlSample = new SettingsModelString(MyJavaMatcherNodeModel.BVL_SAMPLE, "");
    	SettingsModelString bvlMatrixCode = new SettingsModelString(MyJavaMatcherNodeModel.BVL_MATRIX_CODE, "");
    	SettingsModelString bvlSamplingDate = new SettingsModelString(MyJavaMatcherNodeModel.BVL_SAMPLING_DATE, "");
    	SettingsModelString bvlSamplingOrt = new SettingsModelString(MyJavaMatcherNodeModel.BVL_SAMPLING_ORT, "");
    	SettingsModelString bvlBetriebsart = new SettingsModelString(MyJavaMatcherNodeModel.BVL_BETRIEBSART, "");
     	addDialogComponent(new DialogComponentString(sms, "Enter a columnname -> Lab-ProbenNummer:"));
     	addDialogComponent(new DialogComponentString(tpn, "Enter a columnname for Teilprobe:"));
    	addDialogComponent(new DialogComponentString(bvlSample, "Columnname for Sample Vorbefund:"));
    	addDialogComponent(new DialogComponentString(bvlMatrixCode, "Columnname for ADV-Matrix-Code:"));
    	addDialogComponent(new DialogComponentString(bvlSamplingDate, "Columnname for sampling date:"));
    	addDialogComponent(new DialogComponentString(bvlSamplingOrt, "Columnname for sampling Ort:"));
    	addDialogComponent(new DialogComponentString(bvlBetriebsart, "Columnname for Betriebsart:"));

    	createNewGroup("LIMS:"); 
    	SettingsModelString smsLims = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_KUNDENPROBENNR, "");
    	SettingsModelString limsAVV = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_AVV, "");
    	SettingsModelString limsSample = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_SAMPLE, "");
    	SettingsModelString limsMatrixCode = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_MATRIX_CODE, "");
    	SettingsModelString limsSamplingDate = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_SAMPLING_DATE, "");
    	SettingsModelString limsSampleResult = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_SAMPLE_RESULT, "");
    	SettingsModelString limsSampleStatus = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_SAMPLE_STATUS, "");
    	SettingsModelString limsProjectName = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_PROJECT_NAME, "");
    	SettingsModelString limsSamplingOrt = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_SAMPLING_ORT, "");
    	SettingsModelString limsBetriebsart = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_BETRIEBSART, "");
    	SettingsModelString limsID = new SettingsModelString(MyJavaMatcherNodeModel.LIMS_ID, "");
    	addDialogComponent(new DialogComponentString(smsLims, "Enter a columnname -> Kundennummer:"));
    	addDialogComponent(new DialogComponentString(limsAVV, "Enter a columnname -> AVV:"));
    	addDialogComponent(new DialogComponentString(limsSample, "Columnname for Sample Vorbefund:"));
    	addDialogComponent(new DialogComponentString(limsMatrixCode, "Columnname for ADV-Matrix-Code:"));
    	addDialogComponent(new DialogComponentString(limsSamplingDate, "Columnname for sampling date:"));
    	addDialogComponent(new DialogComponentString(limsSampleResult, "Columnname for Sample result:"));
    	addDialogComponent(new DialogComponentString(limsSampleStatus, "Columnname for result status:"));
    	addDialogComponent(new DialogComponentString(limsProjectName, "Columnname for project name:"));
    	addDialogComponent(new DialogComponentString(limsSamplingOrt, "Columnname for sampling Ort:"));
    	addDialogComponent(new DialogComponentString(limsBetriebsart, "Columnname for Betriebsart:"));
    	addDialogComponent(new DialogComponentString(limsID, "Columnname for DB_ID:"));

    	createNewGroup("Other:"); 
    	SettingsModelBoolean bfrView = new SettingsModelBoolean(MyJavaMatcherNodeModel.LIMS_VIEW, Boolean.FALSE);
       	addDialogComponent(new DialogComponentBoolean(bfrView, "BfR View:"));
	}
}

