package com.mrbysco.gnomed.proxy;

import com.mrbysco.gnomed.init.GnomeEntities;

public class ClientProxy extends ServerProxy{
	
	@Override
	public void Preinit(){
		GnomeEntities.RegisterEntityRender();
	}
	
	@Override
	public void Init() {
		
	}
}
