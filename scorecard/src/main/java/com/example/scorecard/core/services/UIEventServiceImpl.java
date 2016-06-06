package com.example.scorecard.core.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;

import com.example.scorecard.common.util.Constants;
import com.example.scorecard.common.util.JsonUtils;
import com.example.scorecard.core.domain.UIEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.eventbus.Subscribe;

public class UIEventServiceImpl implements UIEventService {
	
	private SimpMessagingTemplate messageTemplate_;
	

	public UIEventServiceImpl(final SimpMessagingTemplate messageTemplate) {
		this.messageTemplate_ = messageTemplate;
	}
	

	@Override
	@Subscribe
	public void handleUIEvents(UIEvent event) {
		try {
			messageTemplate_.convertAndSend(Constants.WS_BROKER_ALL_UI_UPDATE_CHANNEL, JsonUtils.convertObjectToJsonString(event));
		} catch (JsonProcessingException e) {
			// if json serialization fails then just send the event-type to ui with null data. UI should implement the behavior 
			// where if it receives an event (that is supposed to have data associated with it) with missing data
			// then query all data pertaining to that subsystem and update.
			//logger_.log(e);
			messageTemplate_.convertAndSend(Constants.WS_BROKER_ALL_UI_UPDATE_CHANNEL, constructJsonStringWithEmptyData(event));
		}
	}
	
	private String constructJsonStringWithEmptyData(UIEvent event) {
		String nullStr = null;
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		builder.append("\"type\":");
		builder.append("\"" + event.getType() + "\"");
		builder.append(",");
		builder.append("\"data\":");
		builder.append(nullStr);
		builder.append("}");
		return builder.toString();
	}
}
