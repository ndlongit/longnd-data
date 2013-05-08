package com.structis.fichesst.server.mapper;

public class MapperId {

	private Object object;

	private String mapId;

	public MapperId(Object object, String mapId) {
		super();
		this.object = object;
		this.mapId = mapId;
	}

	public Object getObject() {
		return object;
	}

	public String getMapId() {
		return mapId;
	}

}
