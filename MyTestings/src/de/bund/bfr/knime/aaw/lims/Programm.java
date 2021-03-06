package de.bund.bfr.knime.aaw.lims;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Programm {

	private String name;
	private String serovarName, tierart, probenahmeort;
	HashSet<String> matrix;
	private int numSamples;
	private HashMap<String, List<Double>> wirkstoffVals;
	private HashMap<Integer, Integer> numResistentArr;
	private int maxResi;
	private HashSet<String> groupResistance;
	private HashMap<String, Integer> groupResistanceCount;
	private HashMap<String, Integer> numPostive;
	private HashMap<String, List<String>> parameterVals;

	public Programm(boolean isSample) {
		numSamples = isSample ? 1 : 0;
		wirkstoffVals = new HashMap<String, List<Double>>();
		parameterVals = new HashMap<String, List<String>>();
		groupResistance = new HashSet<String>();
		numResistentArr = new HashMap<Integer, Integer>();
		maxResi = 0;
		groupResistanceCount = new HashMap<String, Integer>();
		numPostive = new HashMap<String, Integer>();
		matrix = new HashSet<>();
	}

	public int getNumResistent(int i) {
		if (numResistentArr.containsKey(i))
			return numResistentArr.get(i);
		else
			return 0;
	}

	public String getSerovarName() {
		return serovarName;
	}

	public void setSerovarName(String serovarName) {
		this.serovarName = serovarName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public HashSet<String> getMatrices() {
		return matrix;
	}

	public void addMatrix(String matrix) {
		this.matrix.add(matrix);
	}

	public String getTierart() {
		return tierart;
	}

	public void setTierart(String tierart) {
		this.tierart = tierart;
	}

	public String getProbenahmeort() {
		return probenahmeort;
	}

	public void setProbenahmeort(String probenahmeort) {
		this.probenahmeort = probenahmeort;
	}

	public int getNumSamples() {
		return numSamples;
	}

	public HashSet<String> getGroupResistance() {
		return groupResistance;
	}

	public HashMap<String, Integer> getNumPositive() {
		return numPostive;
	}

	public int getMaxResi() {
		return maxResi;
	}

	public HashMap<String, Integer> getGroupResistanceCount() {
		return groupResistanceCount;
	}

	public HashMap<String, List<Double>> getWirkstoffVals() {
		return wirkstoffVals;
	}

	public HashMap<String, List<String>> getParameterVals() {
		return parameterVals;
	}

	private double checkValue(double value) {
		if (value >= 0.008 && value < 0.009) return 0.008;
		else if (value >= 0.01 && value < 0.02) return 0.015;
		else if (value >= 0.03 && value < 0.04) return 0.03125;
		else if (value >= 0.06 && value < 0.07) return 0.0625;
		else if (value >= 0.12 && value < 0.13) return 0.125;
		else if (value >= 0.25 && value < 0.26) return 0.25;
		else if (value >= 0.5 && value < 0.6) return 0.5;
		else return value;
	}
	public void addParameter(Wirkstoff w, String value) {
		List<String> al = new ArrayList<String>();
		al.add(value);
		parameterVals.put(w.getKurz(), al);
	}
	public boolean addWirkstoff(Wirkstoff w, double value, String erreger) {
		boolean result = false;
		double cValue = checkValue(value); 
		double co = w.getCutoff(); // all agents and C. coli
		if (erreger.equals("CA") && serovarName != null && !serovarName.replaceAll("\\s+","").equalsIgnoreCase("C.coli")) { // C. hyointestinalis, C. jejuni, C. coli, C. lari
			co = w.getCutoff2();
		}
		String kurz = w.getKurz();
		int increment = 0;
		if (cValue > co) {
			increment = 1;
			groupResistance.add(w.getGruppe());
			result = true;
		}
		
		if (numPostive.containsKey(kurz))
			numPostive.put(kurz, numPostive.get(kurz) + increment);
		else
			numPostive.put(kurz, increment);
			
		List<Double> al = new ArrayList<Double>();
		al.add(cValue);
		wirkstoffVals.put(kurz, al);
		return result;
	}

	public void sampleFin() {
		numResistentArr.put(groupResistance.size(), 1);
		for (String group : groupResistance) {
			groupResistanceCount.put(group, 1);
		}
		maxResi = groupResistance.size();
	}

	public void merge(Programm p) {
		this.matrix.addAll(p.getMatrices());
		
		int pnr = p.getGroupResistance().size();
		if (!numResistentArr.containsKey(pnr))
			numResistentArr.put(pnr, 1);
		else
			numResistentArr.put(pnr, numResistentArr.get(pnr) + 1);
		if (pnr > maxResi)
			maxResi = pnr;

		numSamples += p.getNumSamples();

		HashMap<String, Integer> pgrc = p.getGroupResistanceCount();
		if (pgrc != null) {
			for (String group : pgrc.keySet()) {
				if (groupResistanceCount.containsKey(group))
					groupResistanceCount.put(group, pgrc.get(group)
							+ groupResistanceCount.get(group));
				else
					groupResistanceCount.put(group, pgrc.get(group));
			}
		}

		HashMap<String, Integer> pw = p.getNumPositive();
		if (pw != null) {
			for (String kurz : pw.keySet()) {
				if (numPostive.containsKey(kurz))
					numPostive.put(kurz, pw.get(kurz) + numPostive.get(kurz));
				else
					numPostive.put(kurz, pw.get(kurz));
			}
		}

		HashMap<String, List<Double>> pwv = p.getWirkstoffVals();
		if (pwv != null) {
			for (String kurz : pwv.keySet()) {
				if (!wirkstoffVals.containsKey(kurz))
					wirkstoffVals.put(kurz, new ArrayList<Double>());
				List<Double> al = wirkstoffVals.get(kurz);
				al.addAll(pwv.get(kurz));
				wirkstoffVals.put(kurz, al);
			}
		}

		HashMap<String, List<String>> ppv = p.getParameterVals();
		if (ppv != null) {
			for (String kurz : ppv.keySet()) {
				if (!parameterVals.containsKey(kurz))
					parameterVals.put(kurz, new ArrayList<String>());
				List<String> al = parameterVals.get(kurz);
				al.addAll(ppv.get(kurz));
				parameterVals.put(kurz, al);
			}
		}
	}

	public HashMap<Double, Integer> getFrequencyMap(String kurz) {
		if (!wirkstoffVals.containsKey(kurz)) return null;
		HashMap<Double, Integer> frequencymap = new HashMap<Double, Integer>();
		List<Double> la = wirkstoffVals.get(kurz);
		for (Double val : la) {
			if (frequencymap.containsKey(val)) {
				frequencymap.put(val, frequencymap.get(val) + 1);
			} else {
				frequencymap.put(val, 1);
			}
		}
		return frequencymap;
	}

	public HashMap<String, Integer> getParamFrequencyMap(String kurz) {
		if (!parameterVals.containsKey(kurz)) return null;
		HashMap<String, Integer> frequencymap = new HashMap<String, Integer>();
		List<String> la = parameterVals.get(kurz);
		for (String val : la) {
			if (frequencymap.containsKey(val)) {
				frequencymap.put(val, frequencymap.get(val) + 1);
			} else {
				frequencymap.put(val, 1);
			}
		}
		return frequencymap;
	}
}
