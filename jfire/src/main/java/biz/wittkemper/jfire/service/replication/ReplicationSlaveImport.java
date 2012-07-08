package biz.wittkemper.jfire.service.replication;

import java.util.List;

import biz.wittkemper.jfire.data.dao.DAOFactory;
import biz.wittkemper.jfire.data.entity.Mitglied;
import biz.wittkemper.jfire.data.entity.Replication;

public class ReplicationSlaveImport {

	public void importReplication(Replication replication) {

		imporMitglieder(replication.getMitglied());

	}

	private void imporMitglieder(List<Mitglied> mitglieder) {
		for (Mitglied mitglied : mitglieder) {
			if (mitgliedAvailable(mitglied)) {
				updateMitglied(mitglied);
			} else {
				saveMitglied(mitglied);
			}

		}

	}

	private void saveMitglied(Mitglied mitglied) {
		mitglied.setMasterId(mitglied.getId());
		mitglied.setId(null);
		DAOFactory.getInstance().getMitgliedDAO().save(mitglied);

	}

	private void updateMitglied(Mitglied mitglied) {
		List<Mitglied> mg = DAOFactory
				.getInstance()
				.getMitgliedDAO()
				.findByQueryString(
						"From Mitglied m WHERE m.masterId = "
								+ mitglied.getId());

		if (mg != null && mg.size() == 1) {
			Mitglied mgt = mg.get(0);
		}
	}

	private boolean mitgliedAvailable(Mitglied mitglied) {
		boolean lreturn = false;
		List<Mitglied> mg = DAOFactory
				.getInstance()
				.getMitgliedDAO()
				.findByQueryString(
						" FROM Mitglied m WHERE m.masterId = "
								+ mitglied.getId());
		if (mg == null || mg.size() == 0 || mg.size() > 1) {

		} else {
			lreturn = true;
		}

		return lreturn;
	}

}
