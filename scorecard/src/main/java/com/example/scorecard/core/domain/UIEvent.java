package com.example.scorecard.core.domain;




/**
 * The Class UIEvent.
 */
public class UIEvent {
	
	private final UIEventType type_;
	private final Object data_;
	
	/**
	 * Instantiates a new UI event.
	 *
	 * @param type the type
	 */
	public UIEvent(final UIEventType type) {
		this.type_ = type;
		this.data_ = null;
	}

	/**
	 * Instantiates a new UI event.
	 *
	 * @param type the type
	 * @param data the data
	 */
	public UIEvent(final UIEventType type, final Object data) {
		this.type_ = type;
		this.data_ = data;
	}

	public UIEventType getType() {
		return type_;
	}
	
	public Object getData() {
		return data_;
	}
	
}
