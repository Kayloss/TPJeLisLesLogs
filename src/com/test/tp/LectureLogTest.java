package com.test.tp;

import static org.junit.jupiter.api.Assertions.*;
import com.groupe5.tp.LectureLog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LectureLogTest {
	
	private File file = null;
	private LectureLog lectureLog = null;
	private String strLogSevere = null;
	private String strLogSevereRun = null;
	private String strLogInfo = null;
	private String strLogInfoRun = null;
	private String strLogInfoNeutral = null;
	private String exitString = null;
	
	@BeforeEach
	public void initTests() {
		file = new File(".\\src\\com\\test\\tp\\test.txt");
		lectureLog = new LectureLog(file);
		strLogSevere = "07-Apr-2021 22:20:37.773 SEVERE [main]  Jenkins stopped org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks L'application web [jenkins] a cree un ThreadLocal avec une cle de type [java.lang.ThreadLocal] (valeur [java.lang.ThreadLocal@66ff37ff]) et une valeur de type [org.springframework.security.core.context.SecurityContextImpl] (valeur [SecurityContextImpl [Null authentication]]) mais ne l'a pas supprime lorsqu'elle a ete arretee, les threads seront graduellement renouveles pour eviter une probable fuite de memoire";
		strLogSevereRun = "07-Apr-2021 22:20:37.773 SEVERE [main] Jenkins is fully up and running org.apache.catalina.loader.WebappClassLoaderBase.checkThreadLocalMapForLeaks L'application web [jenkins] a cree un ThreadLocal avec une cle de type [java.lang.ThreadLocal] (valeur [java.lang.ThreadLocal@66ff37ff]) et une valeur de type [org.springframework.security.core.context.SecurityContextImpl] (valeur [SecurityContextImpl [Null authentication]]) mais ne l'a pas supprime lorsqu'elle a ete arretee, les threads seront graduellement renouveles pour eviter une probable fuite de memoire";
		strLogInfo = "07-Apr-2021 22:20:37.816 INFO [main] Jenkins stopped org.apache.coyote.AbstractProtocol.stop Arret du gestionnaire de protocole [\"http-nio-8080\"]";
		strLogInfoNeutral = "07-Apr-2021 22:20:37.816 INFO [main] org.apache.coyote.AbstractProtocol.stop Arret du gestionnaire de protocole [\"http-nio-8080\"]";
		strLogInfoRun = "07-Apr-2021 22:20:37.816 INFO [main] Jenkins is fully up and running org.apache.coyote.AbstractProtocol.stop Arret du gestionnaire de protocole [\"http-nio-8080\"]";
	}
	

	@Test
	public void rechercheEtatTestSevereSucces() {
		exitString = lectureLog.rechercheEtat(strLogSevereRun);
		if (!exitString.equals(strLogSevereRun+"\nJenkins est en fonctionnement")) {
			fail("Erreur lors du test de rechercheEtatTestSevereSucces");
		}
	}

	@Test
	public void rechercheEtatTestSevereEchec() {
		exitString = lectureLog.rechercheEtat(strLogSevere);
		if (!exitString.equals(strLogSevere+"\nJenkins est arrete")) {
			fail("Erreur lors du test de rechercheEtatTestSevereSucces");
		}
	}
	
	@Test
	public void rechercheEtatTestPasSevereSucces() {
		exitString = lectureLog.rechercheEtat(strLogInfoRun);
		if (!exitString.equals("Jenkins est en fonctionnement")) {
			fail("Erreur lors du test de rechercheEtatTestSevereSucces");
		}
	}

	@Test
	public void rechercheEtatTestPasSevereEchec() {
		exitString = lectureLog.rechercheEtat(strLogInfo);
		if (!exitString.equals("Jenkins est arrete")) {
			fail("Erreur lors du test de rechercheEtatTestSevereSucces");
		}
	}
	
	@Test
	public void rechercheEtatTestPasSevereNeutre() {
		exitString = lectureLog.rechercheEtat(strLogInfoNeutral);
		if (!exitString.equals("")) {
			fail("Erreur lors du test de rechercheEtatTestSevereSucces");
		}
	}

	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}
	*/
}
