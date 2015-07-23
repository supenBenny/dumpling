package org.dumpling.dao.hibernate;

import org.hibernate.cfg.DefaultNamingStrategy;
import org.hibernate.cfg.NamingStrategy;

public class PrefixNamingStrategy extends DefaultNamingStrategy implements NamingStrategy {

	private String prefix = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = -1664684402056330432L;

	@Override
	public String tableName(String tableName) {
		if(this.getPrefix() != null){
			tableName = this.getPrefix() + tableName;
		}
		return super.tableName(tableName);
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		if(this.prefix == null){
			prefix = prefix + "_";
		}
		this.prefix = prefix;
	}

}
