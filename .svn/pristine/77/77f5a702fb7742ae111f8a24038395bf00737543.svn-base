package com.herongtech.console.web.busicontroller.print;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogSystem;

class VelocityAdapter implements LogSystem {
	private VelocityAdapter() {

		Velocity.setProperty(VelocityEngine.RUNTIME_LOG_LOGSYSTEM, this);

		try {
			Velocity.init();
		} catch (Exception ex) {
			// log.warn("Could not initialize the Velocity runtime engine", ex);
		}
	}

	private static VelocityAdapter _instance = new VelocityAdapter();

	public static VelocityAdapter getInstance() {
		return _instance;
	}

	/**
	 * generate template content.
	 * 
	 * @param content
	 *            String
	 * @param context
	 *            Map
	 * @throws Exception
	 *             if there was an error.
	 * @return new rendered content as String
	 */
	public String renderContent(String content, Map context)
			throws IOException, ResourceNotFoundException,
			MethodInvocationException, ParseErrorException {
		if (StringUtils.isBlank(content)) {
			return content;
		}

		if (context == null || context.isEmpty()) {
			return content;
		}

		String output = content; // ??

		// Creates a new instance with the provided storage (and no inner
		// context).
		VelocityContext velocityContext = new VelocityContext(context);

		// now render the template into a Writer, here a StringWriter
		StringWriter writer = new StringWriter();
		Velocity.evaluate(velocityContext, writer, "Velocity", content);
		output = writer.toString();

		return output;
	}

	public void init(RuntimeServices rs) throws Exception {
	}

	public void logVelocityMessage(int arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	/**
	 * logs messages to the great Garbage Collector in the sky
	 * 
	 * @param level
	 *            severity level
	 * @param message
	 *            complete error message
	 */

}