/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.bund.bfr.knime.pmm.core.models.impl;

import de.bund.bfr.knime.pmm.core.models.*;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ModelsFactoryImpl extends EFactoryImpl implements ModelsFactory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static ModelsFactory init() {
		try {
			ModelsFactory theModelsFactory = (ModelsFactory) EPackage.Registry.INSTANCE
					.getEFactory("http:///de/bund/bfr/knime/pmm/core/models.ecore");
			if (theModelsFactory != null) {
				return theModelsFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public ModelsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case ModelsPackage.VARIABLE:
			return createVariable();
		case ModelsPackage.VARIABLE_RANGE:
			return createVariableRange();
		case ModelsPackage.PARAMETER:
			return createParameter();
		case ModelsPackage.PARAMETER_VALUE:
			return createParameterValue();
		case ModelsPackage.PRIMARY_MODEL:
			return createPrimaryModel();
		case ModelsPackage.SECONDARY_MODEL:
			return createSecondaryModel();
		case ModelsPackage.TERTIARY_MODEL:
			return createTertiaryModel();
		case ModelsPackage.PRIMARY_MODEL_FORMULA:
			return createPrimaryModelFormula();
		case ModelsPackage.SECONDARY_MODEL_FORMULA:
			return createSecondaryModelFormula();
		case ModelsPackage.TERTIARY_MODEL_FORMULA:
			return createTertiaryModelFormula();
		case ModelsPackage.STRING_TO_STRING_MAP_ENTRY:
			return (EObject) createStringToStringMapEntry();
		case ModelsPackage.STRING_TO_DOUBLE_MAP_ENTRY:
			return (EObject) createStringToDoubleMapEntry();
		case ModelsPackage.STRING_TO_VARIABLE_RANGE_MAP_ENTRY:
			return (EObject) createStringToVariableRangeMapEntry();
		case ModelsPackage.STRING_TO_PARAMETER_VALUE_MAP_ENTRY:
			return (EObject) createStringToParameterValueMapEntry();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName()
					+ "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Variable createVariable() {
		VariableImpl variable = new VariableImpl();
		return variable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public VariableRange createVariableRange() {
		VariableRangeImpl variableRange = new VariableRangeImpl();
		return variableRange;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Parameter createParameter() {
		ParameterImpl parameter = new ParameterImpl();
		return parameter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ParameterValue createParameterValue() {
		ParameterValueImpl parameterValue = new ParameterValueImpl();
		return parameterValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PrimaryModel createPrimaryModel() {
		PrimaryModelImpl primaryModel = new PrimaryModelImpl();
		return primaryModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SecondaryModel createSecondaryModel() {
		SecondaryModelImpl secondaryModel = new SecondaryModelImpl();
		return secondaryModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public TertiaryModel createTertiaryModel() {
		TertiaryModelImpl tertiaryModel = new TertiaryModelImpl();
		return tertiaryModel;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PrimaryModelFormula createPrimaryModelFormula() {
		PrimaryModelFormulaImpl primaryModelFormula = new PrimaryModelFormulaImpl();
		return primaryModelFormula;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SecondaryModelFormula createSecondaryModelFormula() {
		SecondaryModelFormulaImpl secondaryModelFormula = new SecondaryModelFormulaImpl();
		return secondaryModelFormula;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public TertiaryModelFormula createTertiaryModelFormula() {
		TertiaryModelFormulaImpl tertiaryModelFormula = new TertiaryModelFormulaImpl();
		return tertiaryModelFormula;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Map.Entry<String, String> createStringToStringMapEntry() {
		StringToStringMapEntryImpl stringToStringMapEntry = new StringToStringMapEntryImpl();
		return stringToStringMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Map.Entry<String, Double> createStringToDoubleMapEntry() {
		StringToDoubleMapEntryImpl stringToDoubleMapEntry = new StringToDoubleMapEntryImpl();
		return stringToDoubleMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Map.Entry<String, VariableRange> createStringToVariableRangeMapEntry() {
		StringToVariableRangeMapEntryImpl stringToVariableRangeMapEntry = new StringToVariableRangeMapEntryImpl();
		return stringToVariableRangeMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Map.Entry<String, ParameterValue> createStringToParameterValueMapEntry() {
		StringToParameterValueMapEntryImpl stringToParameterValueMapEntry = new StringToParameterValueMapEntryImpl();
		return stringToParameterValueMapEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ModelsPackage getModelsPackage() {
		return (ModelsPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelsPackage getPackage() {
		return ModelsPackage.eINSTANCE;
	}

} // ModelsFactoryImpl