/*******************************************************************************
 * Copyright (c) 2014 Federal Institute for Risk Assessment (BfR), Germany
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package de.bund.bfr.knime.flink.port;

import java.io.IOException;

import javax.swing.JComponent;

import org.knime.core.node.port.PortObjectSpec;
import org.knime.core.node.port.PortObjectSpecZipInputStream;
import org.knime.core.node.port.PortObjectSpecZipOutputStream;

import de.bund.bfr.knime.flink.FlinkJobManagerSettings;
import de.bund.bfr.knime.flink.SerializationHelper;

/**
 * Represents a connection to the Flink job manager. Currently, the connection is only virtual: With every job
 * submission, we send a completely independent request. However, this special port allows users to keep their Flink
 * configuration at one place through the {@link de.bund.bfr.knime.flink.jm.FlinkJobManagerConnectionNodeModel}.
 */
public class FlinkJobmanagerConnectionObjectSpec implements PortObjectSpec {
	private FlinkJobManagerSettings settings = new FlinkJobManagerSettings();

	/**
	 * Returns the settings.
	 * 
	 * @return the settings
	 */
	public FlinkJobManagerSettings getSettings() {
		return this.settings;
	}

	/*
	 * (non-Javadoc)
	 * @see org.knime.core.node.port.PortObjectSpec#getViews()
	 */
	@Override
	public JComponent[] getViews() {
		return new JComponent[] { new FlinkJobmanagerConnectionObjectView(this.settings) };
	}

	/**
	 * Sets the settings to the specified value.
	 * 
	 * @param settings
	 *        the settings to set
	 */
	public void setSettings(FlinkJobManagerSettings settings) {
		if (settings == null)
			throw new NullPointerException("settings must not be null");

		this.settings = settings;
	}

	public static PortObjectSpecSerializer<FlinkJobmanagerConnectionObjectSpec> getPortObjectSpecSerializer() {
		return new FlinkJobmanagerConnectionObjectSpecSerializer();
	}

	public static class FlinkJobmanagerConnectionObjectSpecSerializer extends
			PortObjectSpecSerializer<FlinkJobmanagerConnectionObjectSpec> {
		@Override
		public FlinkJobmanagerConnectionObjectSpec loadPortObjectSpec(PortObjectSpecZipInputStream in)
				throws IOException {
			FlinkJobmanagerConnectionObjectSpec spec = new FlinkJobmanagerConnectionObjectSpec();
			spec.setSettings(SerializationHelper.<FlinkJobManagerSettings> readObject(in));
			return spec;
		}

		@Override
		public void savePortObjectSpec(FlinkJobmanagerConnectionObjectSpec portObjectSpec,
				PortObjectSpecZipOutputStream out)
				throws IOException {
			SerializationHelper.writeObject(out, portObjectSpec.getSettings());
		}
	}
}
