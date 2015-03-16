package com.silicolife.anote2daemon.wrapper.resources;

import pt.uminho.anote2.resource.classes.IClass;

import com.silicolife.anote2daemon.model.core.entities.Classes;

public class ClassesWrapper {

	public static IClass convertToAnoteStructure(Classes classes) {
		Long id = classes.getId();
		String name = classes.getClass_();
		String color = classes.getClassesColors().getColor();
		IClass classes_ = new pt.uminho.anote2.datastructures.resources.classes.Class(id, name);
		return classes_;

	}

	public static Classes convertToDaemonStructure(IClass classes_) {
		Long id = classes_.getId();
		String name = classes_.getName();
		String color = classes_.getColor();

		Classes classes = new Classes(id, name);
		//classes.setClassesColors(color);

		return classes;
	}
}
