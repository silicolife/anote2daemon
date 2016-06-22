package com.silicolife.anote2daemon.controller.resources;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.silicolife.textmining.core.datastructures.dataaccess.database.dataaccess.implementation.service.resources.IClassesService;
import com.silicolife.textmining.core.interfaces.core.dataaccess.exception.ANoteException;
import com.silicolife.textmining.core.interfaces.core.general.classe.IAnoteClass;

public class ClassPropertiesManagementServer{

	private static Map<Long,IAnoteClass> classIDAnoteClass = new HashMap<>();
	private static Map<String,IAnoteClass> classNameAnoteClass = new HashMap<>();
	
	private static void refreshClasses(IClassesService classesService) throws ANoteException {
		if(classesService != null){
			Set<IAnoteClass> classes = classesService.getClasses();
			classIDAnoteClass = new HashMap<Long, IAnoteClass>();
			classNameAnoteClass = new HashMap<>();
			for(IAnoteClass anoteKlass:classes)
			{
				classIDAnoteClass.put(anoteKlass.getId(), anoteKlass);
				classNameAnoteClass.put(anoteKlass.getName().toLowerCase(), anoteKlass);
			}
		}
	}
	
	public static IAnoteClass getClassIDOrinsertIfNotExist(IClassesService classesService, IAnoteClass klass) throws ANoteException
	{
		if(classNameAnoteClass.containsKey(klass.getName().toLowerCase()))
		{
			return classNameAnoteClass.get(klass.getName().toLowerCase());
		}
		else
		{
			refreshClasses(classesService);
			if(classNameAnoteClass.containsKey(klass.getName().toLowerCase()))
			{
				return classNameAnoteClass.get(klass.getName().toLowerCase());
			}else{
				classesService.addClass(klass);
				classIDAnoteClass.put(klass.getId(), klass);
				classNameAnoteClass.put(klass.getName().toLowerCase(), klass);
				return klass;
			}
		}
	}
	
	public static IAnoteClass getClassGivenClassID(IClassesService classesService, long classID) throws ANoteException
	{
		if(classIDAnoteClass.containsKey(classID))
		{
			return classIDAnoteClass.get(classID);
		}
		else
		{
			refreshClasses(classesService);
			return classIDAnoteClass.get(classID);
		}
	}
	
	public static IAnoteClass getClassIDClassName(IClassesService classesService, String classsName) throws ANoteException
	{
		if(classNameAnoteClass.containsKey(classsName.toLowerCase()))
		{
			return classNameAnoteClass.get(classsName.toLowerCase());
		}
		else
		{
			refreshClasses(classesService);
			return classNameAnoteClass.get(classsName.toLowerCase());
		}
	}

	public static void updateClassName(IClassesService classesService, IAnoteClass klass, String newCLass) throws ANoteException {
//		classesService.updateClassName(klass,newCLass);
		refreshClasses(classesService);
	}

	public static Collection<IAnoteClass> getClasses()
	{
		return classNameAnoteClass.values();
	}

	public static boolean containsClass(IClassesService classesService, String newCLass) throws ANoteException {
		return getClassIDClassName(classesService,newCLass)!=null;
	}
}

