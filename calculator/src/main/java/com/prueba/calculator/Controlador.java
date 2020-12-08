package com.prueba.calculator;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200",maxAge=3600)
@RestController
@RequestMapping({"/calculadora"})
public class Controlador {
	
	@Autowired
	CalculadoraService service;
	
	@PostMapping({"/save"})
	public Calculadora save(@RequestBody Calculadora c) {
		return service.add(c);
	}
	
	@GetMapping(path= {"calcular/{semana}/{idtecnico}"})
	public Reportehoras calcular(@PathVariable("idtecnico") String idtecnico, @PathVariable("semana") int semana) throws ParseException {
		List<Calculadora> responBD ;
		Reportehoras tipoHora= new Reportehoras();
		responBD=service.calcular(idtecnico);
		System.out.println ("RepondBD "+ responBD.size());
		for (Calculadora cal : responBD) {
			
				if(isSemana(semana, cal.getFinicio(), cal.getFfin())) {
					System.out.println ("isSemana true cal.getFinicio()" + cal.getFinicio()+" cal.getFfin()"+cal.getFfin());
					tipoHora.setNormales(horasNormales(cal.getFinicio(), cal.getFfin(),tipoHora.getNormalesextras()));
					tipoHora.setNocturnas(horasNocturnas(cal.getFinicio(),cal.getFfin(),tipoHora.getNocturasextras()));
					tipoHora.setDominicales(horasDominicales(cal.getFfin(),cal.getFfin()));
					
				}			
		}
		System.out.println ("RepondBD "+ tipoHora.getNormales());	
		return tipoHora;
	}
	
	public boolean isSemana(int semana, String fechainicio, String fechaFin) throws ParseException {
		//java.util.Date date = new java.util.Date(fechainicio);
		//java.sql.Date inputDate = new java.sql.Date(date.getTime());
		if(!fechainicio.isEmpty() && !fechaFin.isEmpty() ) {
		java.util.Date date =  new SimpleDateFormat("yyyy-MM-dd").parse(fechainicio);
		java.sql.Date inputDate = new java.sql.Date(date.getTime());
		 Calendar calendar = Calendar.getInstance();
	     calendar.setTime(inputDate);
	     int numberWeekOfYearIni = calendar.get(Calendar.WEEK_OF_YEAR);
	     
	     java.util.Date datef =  new SimpleDateFormat("yyyy-MM-dd").parse(fechaFin);
	     java.sql.Date inputDatef = new java.sql.Date(datef.getTime());
		// java.sql.Date inputDatef = new java.sql.Date(datef.getTime());
	     
		 Calendar calendarf = Calendar.getInstance();
		 calendarf.setTime(inputDatef);
	     int numberWeekOfYearInif = calendarf.get(Calendar.WEEK_OF_YEAR);
	     
	     System.out.println ("method isSemana numberWeekOfYearIni "+ numberWeekOfYearIni +" numberWeekOfYearInif "+numberWeekOfYearInif );
	     
	     if(semana==numberWeekOfYearIni && semana==numberWeekOfYearInif) {
	    	 return true;
	     }
	     else {
	    	 return false;
	     }
		}
		
		return false;
		
	}
	
	public int horasNormales(String fechaInicio, String fechaFin, int hextra) throws ParseException {
		
	//	java.util.Date date = new java.util.Date(fechaInicio);
		//java.sql.Date inputDatein = new java.sql.Date(date.getTime());
		java.util.Date date =  new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaInicio);
		java.sql.Date inputDatein = new java.sql.Date(date.getTime());
		
		java.util.Date datef =  new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaFin);
	     java.sql.Date inputDatef = new java.sql.Date(datef.getTime());
		//Date inputDatef = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaFin);
		Calendar calendar = Calendar.getInstance();
		Calendar calendarf = Calendar.getInstance();
		calendar.setTime(inputDatein);
		calendarf.setTime(inputDatef);
		int cont=0;
		
		
		int hourOfDayIni = calendar.get(Calendar.HOUR_OF_DAY);
		int hourOfDayFin = calendarf.get(Calendar.HOUR_OF_DAY);
		
		System.out.println ("NOR: hourOfDayIni"+hourOfDayIni);
		System.out.println ("NOR: hourOfDayFin"+hourOfDayFin);
		
		if(hourOfDayIni >= 7 && hourOfDayFin<=20 ) {
			
			System.out.println ("NOR: betwen 7 y 20 ");
			for(int i=hourOfDayIni;i <hourOfDayFin; i++) {
				cont=cont+1;
			}
			System.out.println ("NOR: CONT "+ cont);
		}
		
		if (hourOfDayIni<7) {			
			hextra+=7-hourOfDayIni;
			System.out.println ("NOR: antes de 7 extra "+ hextra);
		}
		
		if(hourOfDayFin>20) {			
			hextra+=hourOfDayIni-20;
			System.out.println ("NOR: despues de20 extra "+ hextra);
		}
		
		return cont;		
		
	}
	
public int horasNocturnas(String fechaInicio, String fechaFin, int hextra) throws ParseException {
		
		java.util.Date date =  new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaInicio);
		java.sql.Date inputDatein = new java.sql.Date(date.getTime());
	
		java.util.Date datef =  new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaFin);
		java.sql.Date inputDatef = new java.sql.Date(datef.getTime());
	//	Date inputDatein = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaInicio);
//		Date inputDatef = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaFin);
		Calendar calendar = Calendar.getInstance();
		Calendar calendarf = Calendar.getInstance();
		calendar.setTime(inputDatein);
		calendarf.setTime(inputDatef);
		int cont=0;
		
		int hourOfDayIni = calendar.get(Calendar.HOUR_OF_DAY);
		int hourOfDayFin = calendarf.get(Calendar.HOUR_OF_DAY);
		
		System.out.println ("NOC: hourOfDayIni"+hourOfDayIni);
		System.out.println ("NOC: hourOfDayFin"+hourOfDayFin);
		
		if(hourOfDayIni >= 20 && hourOfDayIni<=23 && hourOfDayFin>=20 && hourOfDayFin<=23 ) {			
			for(int i=hourOfDayIni;i <=24; i++) {
				cont=cont+1;
			}
			
			System.out.println ("NOC: betewn 20 y 24 "+cont);
			
		}
		
		if(hourOfDayIni >=0 && hourOfDayIni <=7 && hourOfDayFin>=1 && hourOfDayFin<=7) {
			for(int i=0;i <=hourOfDayFin; i++) {
				cont=cont+1;
			}
			
			System.out.println ("NOC: betewn 1 y 7 "+cont);
		}
		
		if(hourOfDayIni >=20 && hourOfDayIni <=24 && hourOfDayFin>=0 && hourOfDayFin<=7) {
			for(int i=hourOfDayIni;i <=24; i++) {
				cont=cont+1;
			}
			
			for(int i=0;i <=hourOfDayFin; i++) {
				cont=cont+1;
			}
			
			System.out.println ("NOC: noche y mañana "+cont);
		}
		
		if (hourOfDayIni<20) {			
			hextra+=20-hourOfDayIni;
			System.out.println ("NOC: extra antes de 20"+hextra);
		}
		
		if(hourOfDayFin>7) {			
			hextra+=hourOfDayIni-7;
			System.out.println ("NOC: extra despues de 7"+hextra);
		}
		
		return cont;		
		
	}

public int horasDominicales(String fechaInicio, String fechaFin) throws ParseException {
	
	java.util.Date date =  new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaInicio);
	java.sql.Date inputDatein = new java.sql.Date(date.getTime());
	
	java.util.Date datef =  new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaFin);
    java.sql.Date inputDatef = new java.sql.Date(datef.getTime());
	//Date inputDatein = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaInicio);
	//Date inputDatef = (Date) new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fechaFin);
	Calendar calendar = Calendar.getInstance();
	Calendar calendarf = Calendar.getInstance();
	calendar.setTime(inputDatein);
	calendarf.setTime(inputDatef);
	int cont=0;
	
	int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	
	 if (dayOfWeek != Calendar.SUNDAY) {

		 int hourOfDayIni = calendar.get(Calendar.HOUR_OF_DAY);
		 int hourOfDayFin = calendarf.get(Calendar.HOUR_OF_DAY);
		 
		 System.out.println ("DOM: hourOfDayIni"+hourOfDayIni);
			System.out.println ("DOM: hourOfDayFin"+hourOfDayFin);
		 
		 cont=hourOfDayFin-hourOfDayIni;
		 
		 System.out.println ("DOM: cont"+cont);
	 }
	 return cont;
}


}
