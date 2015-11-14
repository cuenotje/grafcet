package fr.grafcet.bd;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import fr.grafcet.persistence.xml.Grafcet;

/** persistence au format XML dans un dossier "save" */
public class XMLGrafcetPersister extends GrafcetBD {

    private static final String DEFAULT_SAVE_DIR = "save";

    private static Map<ExistingGrafcetDTO, Path> map = new HashMap<ExistingGrafcetDTO, Path>();

    @Override
    public List<ExistingGrafcetDTO> getExistingGrafcet() throws IOException {
	List<ExistingGrafcetDTO> results = new ArrayList<ExistingGrafcetDTO>();
	Path defaultSaveFolder = getDefautlStorePath();
	if (Files.exists(defaultSaveFolder)) {
	    DirectoryStream<Path> stream = Files.newDirectoryStream(defaultSaveFolder);
	    try {
		Iterator<Path> iterator = stream.iterator();
		while (iterator.hasNext()) {
		    Path p = iterator.next();
		    ExistingGrafcetDTO dto = new ExistingGrafcetDTO();
		    dto.setIdentifier(p.toFile().getName());
		    map.put(dto, p);
		    results.add(dto);
		}
	    } finally {
		stream.close();
	    }
	}
	return results;
    }

    @Override
    public GrafcetDTO loadGrafcet(ExistingGrafcetDTO grafcetToLoad) throws IOException {
	GrafcetDTO result = null;
	try {
	    Path path = map.get(grafcetToLoad);
	    if (null != path) {
		JAXBContext context = JAXBContext.newInstance(Grafcet.class);
		Unmarshaller m = context.createUnmarshaller();
		Grafcet grafcet = (Grafcet) m.unmarshal(path.toFile());
		result = XMLObjectConverter.convertToObject(grafcet);
	    }
	} catch (JAXBException e) {
	    throw new IOException("Erreur de transformation objet -> xml.", e);
	}
	return result;
    }

    @Override
    public void saveGrafcet(GrafcetDTO grafcetProject, File saveDirectory) throws IOException {
	try {
	    Grafcet grafcet = XMLObjectConverter.convertToXmlObject(grafcetProject);

	    JAXBContext context = JAXBContext.newInstance(Grafcet.class);
	    Marshaller m = context.createMarshaller();
	    // formattage du xml
	    m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

	    // debug sur System.out
	    // m.marshal(grafcet, System.out);

	    // ecriture du fichier
	    Path filePath = getPathToSaved(saveDirectory).resolve(grafcet.getProjectName() + ".xml");
	    m.marshal(grafcet, filePath.toFile());
	} catch (JAXBException e) {
	    throw new IOException("Erreur de transformation objet -> xml.", e);
	}
    }

    private Path getPathToSaved(File saveDirectory) throws IOException {
	Path pathToSaved = null;
	if (null != saveDirectory) {
	    pathToSaved = Paths.get(saveDirectory.toURI());
	} else {
	    pathToSaved = getDefautlStorePath();
	}
	return pathToSaved;
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
