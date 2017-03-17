package com.herongtech.mqchannel.pkg;

import com.herongtech.console.cache.ChannelFieldCache;

public class MqChannelFieldCache {
	
	public static void initFieldConfig(){
		ChannelFieldCache.getInstance("mqchannel");
	}
	
	public static ChannelFieldCache getFieldChannelCache(){
		return ChannelFieldCache.getInstance("mqchannel");
	}
	
	public static void refreshFieldConfig(){
		ChannelFieldCache.refresh("mqchannel");
	}
}
