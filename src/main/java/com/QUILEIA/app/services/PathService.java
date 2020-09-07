package com.QUILEIA.app.services;

import com.QUILEIA.app.model.Path;

public interface PathService {
	
	public Path findPathById(int pId);
	
	public Iterable<Path> findAllPaths();
	
	public boolean addPath(int pId, String pTipoVia, String pStrOrKr, int pNumber, int pTraffic);
	
	public void editPath(int pId, String pTipoVia, String pStrOrKr, int pNumber, int pTraffic);
	
	public void deletePath(int pId);
}
