package de.fzi.cep.sepa.runtime.activity.detection.utils;

import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;

import de.fzi.cep.sepa.commons.config.ClientConfiguration;
import de.fzi.cep.sepa.model.builder.EpProperties;
import de.fzi.cep.sepa.model.impl.eventproperty.EventProperty;
import de.fzi.cep.sepa.model.impl.output.FixedOutputStrategy;
import de.fzi.cep.sepa.model.impl.output.OutputStrategy;
import de.fzi.cep.sepa.runtime.activity.detection.main.ModelInvocationRequestParameters;

public class Utils {
	
	public static OutputStrategy getActivityDetection() {
		List<EventProperty> outputProperties = new ArrayList<>();
		outputProperties.add(EpProperties.stringEp("activityId", "http://schema.org/Text"));
		outputProperties.add(EpProperties.longEp("startTime", "http://schema.org/Date"));
		outputProperties.add(EpProperties.longEp("endTime", "http://schema.org/Date"));
		outputProperties.add(EpProperties.stringEp("description", "http://schema.org/Text"));

		return new FixedOutputStrategy(outputProperties);
	}
	
	public static ModelInvocationRequestParameters getModelInvocationRequestParameters(String pipelineId, int modelId, String inputTopic,
			String outputTopic) {
		return new ModelInvocationRequestParameters(pipelineId, modelId,
				ClientConfiguration.INSTANCE.getZookeeperHost(), ClientConfiguration.INSTANCE.getZookeeperPort(),
				inputTopic, ClientConfiguration.INSTANCE.getKafkaHost(), ClientConfiguration.INSTANCE.getKafkaPort(),
				outputTopic);
	}
	
	public static JsonObject getModelInvocationMessage(ModelInvocationRequestParameters params) {
		
		return Json.createObjectBuilder().add("pipelineId", params.getPipelineId()).add("analyticsOperation", "ActivityDetection")
				.add("modelId", params.getModelId())
				.add("input",
						Json.createObjectBuilder().add("zookeeperHost", params.getZookeeperHost())
								.add("zookeeperPort", params.getZookeeperPort()).add("inputTopic", params.getInputTopic()))
				.add("output", Json.createObjectBuilder().add("kafkaHost", params.getKafkaHost())
						.add("kafkaPort", params.getKafkaPort()).add("outputTopic", params.getOutputTopic()))
				.build();
	}

}
