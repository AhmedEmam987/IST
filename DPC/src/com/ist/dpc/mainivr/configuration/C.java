package com.ist.dpc.mainivr.configuration;
import com.ist.tools.v2.util.config.Configurator;




public class C {

	public  static Configurator CONFIGURATOR = Configurator.configure(C.class.getResourceAsStream("settings.properties"), C.class.getResourceAsStream("log4j.properties"),C.class.getName());
	
}
