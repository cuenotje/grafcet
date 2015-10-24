package fr.grafcet.bd;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import fr.grafcet.persistence.xml.GInitialStep;
import fr.grafcet.persistence.xml.Grafcet;
import fr.grafcet.ui.elements.GInitialStepUI;

/** persistence au format XML dans un dossier "save" */
public class XMLGrafcetPersister extends GrafcetBD {

    private static final String DEFAULT_SAVE_DIR = "save";

    @Override
    public List<GInitialStepUI> getExistingGrafcet() throws IOException {
	List<GInitialStepUI> results = new ArrayList<GInitialStepUI>();
	Path defaultSaveFolder = getDefautlStorePath();
	if (Files.deleteIfExists(defaultSaveFolder)) {
	    DirectoryStream<Path> stream = Files.newDirectoryStream(defaultSaveFolder);
	    try {
		Iterator<Path> iterator = stream.iterator();
		while (iterator.hasNext()) {
		    Path p = iterator.next();
		    System.out.println(p);
		}
	    } finally {
		stream.close();
	    }
	}
	return results;
    }

    @Override
    public void saveGrafcet(GInitialStepUI grafcet, String projectName) throws IOException {
	Path defaultSaveFolder = getDefautlStorePath();
	// Sérialisation des grafcets
	persists(convertToXmlObject(grafcet), defaultSaveFolder, projectName);
    }

    private void persists(Grafcet grafcet, Path outputDir, String projectName) throws IOException {
	try {
	    JAXBContext context = JAXBContext.newInstance(Grafcet.class);
	    Marshaller m = context.createMarshaller();
	    // formattage du xml
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // debug sur System.out
	    // m.marshal(grafcet, System.out);

	    // ecriture du fichier
	    Path filePath = outputDir.resolve(projectName + ".xml");
	    m.marshal(grafcet, filePath.toFile());
	} catch (JAXBException e) {
	    new IOException("Erreur de transformation objet -> xml.", e);
	}
    }

    private Grafcet convertToXmlObject(GInitialStepUI grafcet) {
	Grafcet g = XMLObjectFactory.getInstance().createGrafcet();
	GInitialStep initialStep = convertInitialStepUI(grafcet);
	// FIXME gerer le parsing de tout le grafcet
	g.setInitialStep(initialStep);
	return g;
    }

    private GInitialStep convertInitialStepUI(GInitialStepUI initialStepUI) {
	GInitialStep istep = XMLObjectFactory.getInstance().createGInitialStep();
	istep.setGridRowIndex(initialStepUI.getGridRowIndex());
	istep.setGridColumnIndex(initialStepUI.getGridColumnIndex());
	istep.setName(initialStepUI.getModel().getName());
	return istep;
    }

    private Path getDefautlStorePath() throws IOException {
	Path path = Paths.get(System.getProperty("user.dir"));
	Path defaultSaveFolder = path.resolve(DEFAULT_SAVE_DIR);
	if (Files.notExists(defaultSaveFolder)) {
	    Files.createDirectories(defaultSaveFolder);
	}
	return defaultSaveFolder;
    }
}
