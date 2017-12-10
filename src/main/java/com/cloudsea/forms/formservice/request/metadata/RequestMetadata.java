package com.cloudsea.forms.formservice.request.metadata;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RequestMetadata {

	@Id
	private String id;
	private String userAgent;
	private String remoteIPAddress;
	private String userLocale;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getRemoteIPAddress() {
		return remoteIPAddress;
	}

	public void setRemoteIPAddress(String remoteIPAddress) {
		this.remoteIPAddress = remoteIPAddress;
	}

	public String getUserLocale() {
		return userLocale;
	}

	public void setUserLocale(String userLocale) {
		this.userLocale = userLocale;
	}

	@Override
	public String toString() {
		return "RequestMetadata [id=" + id + ", userAgent=" + userAgent + ", remoteIPAddress=" + remoteIPAddress
				+ ", userLocale=" + userLocale + "]";
	}

}
