/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.boomenvanden.tryouts.javapersistenceapi.eclipselink;

import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.sessions.Session;

/**
 *
 * @author mark
 */
public class EnableIntegrityChecker implements SessionCustomizer {

    public void customize(Session session) throws Exception {
        session.getIntegrityChecker().checkDatabase();
        session.getIntegrityChecker().setShouldCatchExceptions(false);
    }
}
