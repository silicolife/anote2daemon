package com.silicolife.anote2daemon.controller.hyperlink;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.exceptions.HyperLinkMenuException;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.security.Permissions;
import pt.uminho.anote2.datastructures.dataaccess.database.dataaccess.implementation.service.hyperlink.IHyperLinkService;
import pt.uminho.anote2.datastructures.hyperlink.HyperLinkMenuItemImpl;
import pt.uminho.anote2.interfaces.core.dataaccess.exception.ANoteException;
import pt.uminho.anote2.interfaces.core.general.source.ISource;
import pt.uminho.anote2.interfaces.core.hyperlink.IHyperLinkMenuItem;

import com.silicolife.anote2daemon.security.RestPermissionsEvaluatorEnum;
import com.silicolife.anote2daemon.utils.GenericPairSpringSpel;
import com.silicolife.anote2daemon.webservice.DaemonResponse;

/**
 * The goal of this class is to expose for the web all Hyperlink functionalities of
 * anote2daemon. It is necessary a user logged to access these methods
 * 
 * @author Joel Azevedo Costa
 * @year 2015
 *
 */
@RequestMapping(value = "/hyperlink", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@ResponseBody
@Controller
public class HyperlinkController {

	@Autowired
	private Permissions permissions;
	@Autowired
	private GenericPairSpringSpel<RestPermissionsEvaluatorEnum, List<String>> genericPairSpringSpel;
	@Autowired
	private IHyperLinkService hyperlinkService;
	
	/**
	 * Get hyperlink menu item
	 * 
	 * @param hyperLinkId
	 * @return
	 * @throws HyperLinkMenuException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getHyperLinkMenuItemById/{hyperLinkId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<IHyperLinkMenuItem>> getHyperLinkMenuItemByID(@PathVariable Long hyperLinkId) throws HyperLinkMenuException {
		DaemonResponse<IHyperLinkMenuItem> response = new DaemonResponse<IHyperLinkMenuItem>(hyperlinkService.getHyperLinkMenuItemByID(hyperLinkId));
		return new ResponseEntity<DaemonResponse<IHyperLinkMenuItem>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get all hyperlink menus
	 * 
	 * @return
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllHyperLinkMenuItems", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IHyperLinkMenuItem>>> getAllHyperLinkMenuItems() {
		DaemonResponse<List<IHyperLinkMenuItem>> response = new DaemonResponse<List<IHyperLinkMenuItem>>(hyperlinkService.getAllHyperLinkMenuItems());
		return new ResponseEntity<DaemonResponse<List<IHyperLinkMenuItem>>>(response, HttpStatus.OK);
	}
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/updateSourcesHyperLinkMenuItem", method = RequestMethod.POST)
	public ResponseEntity<DaemonResponse<Boolean>> updateSourcesHyperLinkMenuItem(@RequestBody  HyperLinkMenuItemImpl hyperLinkMenuItem) throws HyperLinkMenuException{
		DaemonResponse<Boolean> response = new DaemonResponse<Boolean>(hyperlinkService.updateSourcesHyperLinkMenuItem(hyperLinkMenuItem));
		return new ResponseEntity<DaemonResponse<Boolean>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get All sources
	 * 
	 * @return
	 * @throws ANoteException 
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getAllSources", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<ISource>>> getAllSources() throws HyperLinkMenuException{
		DaemonResponse<List<ISource>> response = new DaemonResponse<List<ISource>>(hyperlinkService.getAllSources());
		return new ResponseEntity<DaemonResponse<List<ISource>>>(response, HttpStatus.OK);
	}
	
	/**
	 * Get Hyperlink by source
	 * 
	 * @param sourceId
	 * @return
	 * @throws HyperLinkMenuException
	 */
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/getHyperLinkMenuItemsForSource/{sourceId}", method = RequestMethod.GET)
	public ResponseEntity<DaemonResponse<List<IHyperLinkMenuItem>>> getHyperLinkMenuItemsForSource(@PathVariable Long sourceId) throws HyperLinkMenuException {
		DaemonResponse<List<IHyperLinkMenuItem>> response = new DaemonResponse<List<IHyperLinkMenuItem>>(hyperlinkService.getHyperLinkMenuItemsForSource(sourceId));
		return new ResponseEntity<DaemonResponse<List<IHyperLinkMenuItem>>>(response, HttpStatus.OK);
	}
}
