eventCompileStart = {target ->
	println 'i18n event compile start'
	compileAST(basedir, classesDir, pluginsDirPath, projectWorkDir, getPluginInfoForName('i18n-fields').version)
}

def compileAST(def srcBaseDir, def srcDestDir, def pluginsDir, def projectWork, def pluginVersion) {
	
	ant.sequential {
		println "$projectWork"
		
		echo "Precompiling AST Transformations ..."
		
		path id: "grails.compile.classpath", compileClasspath
		def classpathId = "grails.compile.classpath"
		mkdir dir: srcDestDir
		mkdir dir: "$projectWork/plugin-classes"
		
		def dirs = [
			"$srcBaseDir/src/groovy/i18nfields" : "$srcDestDir",
			"$pluginsDir/i18n-fields-$pluginVersion/src/groovy/i18nfields" : "$projectWork/plugin-classes"
		]
		
		echo "compiling from directories ${dirs}"
		
		dirs.each { srcDir, destDir ->
			if (new File(srcDir).exists()) {
				groovyc(
					destdir: destDir,
					srcDir: srcDir,
					classpathref: classpathId,
					verbose: grailsSettings.verboseCompile,
					stacktrace: "yes",
					encoding: "UTF-8"
				)
			}
		}
		echo "done precompiling AST Transformations"
	}
}
