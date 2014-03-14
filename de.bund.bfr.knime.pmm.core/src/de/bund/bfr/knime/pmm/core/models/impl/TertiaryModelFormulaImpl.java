/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.bund.bfr.knime.pmm.core.models.impl;

import de.bund.bfr.knime.pmm.core.models.ModelsPackage;
import de.bund.bfr.knime.pmm.core.models.Parameter;
import de.bund.bfr.knime.pmm.core.models.TertiaryModelFormula;
import de.bund.bfr.knime.pmm.core.models.Variable;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tertiary Model Formula</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link de.bund.bfr.knime.pmm.core.models.impl.TertiaryModelFormulaImpl#getId
 * <em>Id</em>}</li>
 * <li>
 * {@link de.bund.bfr.knime.pmm.core.models.impl.TertiaryModelFormulaImpl#getName
 * <em>Name</em>}</li>
 * <li>
 * {@link de.bund.bfr.knime.pmm.core.models.impl.TertiaryModelFormulaImpl#getFormula
 * <em>Formula</em>}</li>
 * <li>
 * {@link de.bund.bfr.knime.pmm.core.models.impl.TertiaryModelFormulaImpl#getDepVar
 * <em>Dep Var</em>}</li>
 * <li>
 * {@link de.bund.bfr.knime.pmm.core.models.impl.TertiaryModelFormulaImpl#getParams
 * <em>Params</em>}</li>
 * <li>
 * {@link de.bund.bfr.knime.pmm.core.models.impl.TertiaryModelFormulaImpl#getIndepVars
 * <em>Indep Vars</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TertiaryModelFormulaImpl extends EObjectImpl implements
		TertiaryModelFormula {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFormula() <em>Formula</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFormula()
	 * @generated
	 * @ordered
	 */
	protected static final String FORMULA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFormula() <em>Formula</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFormula()
	 * @generated
	 * @ordered
	 */
	protected String formula = FORMULA_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDepVar() <em>Dep Var</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDepVar()
	 * @generated
	 * @ordered
	 */
	protected Variable depVar;

	/**
	 * The cached value of the '{@link #getParams() <em>Params</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getParams()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> params;

	/**
	 * The cached value of the '{@link #getIndepVars() <em>Indep Vars</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getIndepVars()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> indepVars;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TertiaryModelFormulaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelsPackage.Literals.TERTIARY_MODEL_FORMULA;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelsPackage.TERTIARY_MODEL_FORMULA__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelsPackage.TERTIARY_MODEL_FORMULA__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getFormula() {
		return formula;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setFormula(String newFormula) {
		String oldFormula = formula;
		formula = newFormula;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelsPackage.TERTIARY_MODEL_FORMULA__FORMULA, oldFormula,
					formula));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Variable getDepVar() {
		return depVar;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDepVar(Variable newDepVar,
			NotificationChain msgs) {
		Variable oldDepVar = depVar;
		depVar = newDepVar;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR, oldDepVar,
					newDepVar);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDepVar(Variable newDepVar) {
		if (newDepVar != depVar) {
			NotificationChain msgs = null;
			if (depVar != null)
				msgs = ((InternalEObject) depVar)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR,
								null, msgs);
			if (newDepVar != null)
				msgs = ((InternalEObject) newDepVar)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR,
								null, msgs);
			msgs = basicSetDepVar(newDepVar, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR, newDepVar,
					newDepVar));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Parameter> getParams() {
		if (params == null) {
			params = new EObjectContainmentEList<Parameter>(Parameter.class,
					this, ModelsPackage.TERTIARY_MODEL_FORMULA__PARAMS);
		}
		return params;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Variable> getIndepVars() {
		if (indepVars == null) {
			indepVars = new EObjectContainmentEList<Variable>(Variable.class,
					this, ModelsPackage.TERTIARY_MODEL_FORMULA__INDEP_VARS);
		}
		return indepVars;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR:
			return basicSetDepVar(null, msgs);
		case ModelsPackage.TERTIARY_MODEL_FORMULA__PARAMS:
			return ((InternalEList<?>) getParams()).basicRemove(otherEnd, msgs);
		case ModelsPackage.TERTIARY_MODEL_FORMULA__INDEP_VARS:
			return ((InternalEList<?>) getIndepVars()).basicRemove(otherEnd,
					msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ModelsPackage.TERTIARY_MODEL_FORMULA__ID:
			return getId();
		case ModelsPackage.TERTIARY_MODEL_FORMULA__NAME:
			return getName();
		case ModelsPackage.TERTIARY_MODEL_FORMULA__FORMULA:
			return getFormula();
		case ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR:
			return getDepVar();
		case ModelsPackage.TERTIARY_MODEL_FORMULA__PARAMS:
			return getParams();
		case ModelsPackage.TERTIARY_MODEL_FORMULA__INDEP_VARS:
			return getIndepVars();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ModelsPackage.TERTIARY_MODEL_FORMULA__ID:
			setId((String) newValue);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__NAME:
			setName((String) newValue);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__FORMULA:
			setFormula((String) newValue);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR:
			setDepVar((Variable) newValue);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__PARAMS:
			getParams().clear();
			getParams().addAll((Collection<? extends Parameter>) newValue);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__INDEP_VARS:
			getIndepVars().clear();
			getIndepVars().addAll((Collection<? extends Variable>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ModelsPackage.TERTIARY_MODEL_FORMULA__ID:
			setId(ID_EDEFAULT);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__FORMULA:
			setFormula(FORMULA_EDEFAULT);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR:
			setDepVar((Variable) null);
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__PARAMS:
			getParams().clear();
			return;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__INDEP_VARS:
			getIndepVars().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ModelsPackage.TERTIARY_MODEL_FORMULA__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case ModelsPackage.TERTIARY_MODEL_FORMULA__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT
					.equals(name);
		case ModelsPackage.TERTIARY_MODEL_FORMULA__FORMULA:
			return FORMULA_EDEFAULT == null ? formula != null
					: !FORMULA_EDEFAULT.equals(formula);
		case ModelsPackage.TERTIARY_MODEL_FORMULA__DEP_VAR:
			return depVar != null;
		case ModelsPackage.TERTIARY_MODEL_FORMULA__PARAMS:
			return params != null && !params.isEmpty();
		case ModelsPackage.TERTIARY_MODEL_FORMULA__INDEP_VARS:
			return indepVars != null && !indepVars.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(", formula: ");
		result.append(formula);
		result.append(')');
		return result.toString();
	}

} // TertiaryModelFormulaImpl