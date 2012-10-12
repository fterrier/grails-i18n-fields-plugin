package i18nfields

import grails.util.GrailsUtil

class ConfigProvider {
	
	static final CONFIG_LOCATION = "${System.properties['user.dir']}/grails-app/conf/Config.groovy"

	def getConfig() {
		try {
			return new ConfigSlurper(GrailsUtil.environment).parse(new File(CONFIG_LOCATION).toURL())
		} catch (Exception e) {
			println "[i18nFields] WARNING - Exception while reading configuration, ${e}"
			return null
		}
	}
	
}
