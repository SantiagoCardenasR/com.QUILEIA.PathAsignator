package com.QUILEIA.app.services;

import org.springframework.stereotype.Service;
import com.QUILEIA.app.model.Path;
import com.QUILEIA.app.repos.PathRepo;

@Service
public class PathServiceImpl implements PathService {
	
	private final PathRepo pathsRepo;
	
	public PathServiceImpl(PathRepo pPathsRepo) {
		super();
		pathsRepo = pPathsRepo;
	}

	@Override
	public Path findPathById(int pId) {
		return pathsRepo.findById(pId).get();
	}

	@Override
	public Iterable<Path> findAllPaths() {
		return pathsRepo.findAll();
	}

	@Override
	public boolean addPath(int pId, String pTipoVia, String pStrOrKr, int pNumber, int pTraffic) {
		if(pTraffic <= 100 && pTraffic > 0) {
			Path newPath = new Path(pId, pTipoVia, pStrOrKr, pNumber, pTraffic);
			pathsRepo.save(newPath);
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public void editPath(int pId, String pTipoVia, String pStrOrKr, int pNumber, int pTraffic) {
		Path pathToUpdate = pathsRepo.findById(pId).get();
		pathToUpdate.setType(pTipoVia);
		pathToUpdate.setIsStreetOrKr(pStrOrKr);
		pathToUpdate.setNumber(pNumber);
		pathToUpdate.setTraffic(pTraffic);
		pathsRepo.save(pathToUpdate);
		
	}

	@Override
	public void deletePath(int pId) {
		pathsRepo.deleteById(pId);
	}

}
