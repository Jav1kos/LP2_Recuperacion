package com.cibertec.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cibertec.model.ciudad;
import com.cibertec.model.venta;
import com.cibertec.model.detalle_venta;
import com.cibertec.repository.ICiudadRepository;
import com.cibertec.repository.IDetalleRepository;
import com.cibertec.repository.IVentaRepository;

@Controller
public class ventaController {

	@Autowired
	private ICiudadRepository ciudadRepository;
	
	@Autowired 
	private IVentaRepository ventaRepository;
	
	@Autowired
	private IDetalleRepository detalleRepository;
	
	@GetMapping("/")
    public String redirectToVenta() {
        return "redirect:/venta";
    }
	
	@GetMapping("/venta")
	public String listaCiudades(Model model) {
		List<ciudad> lista_ciudades = ciudadRepository.findAll();
		model.addAttribute("lista_ciudades",lista_ciudades);
		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregarVenta(
			@RequestParam("origen") String origen,
			@RequestParam("destino") String destino,
            @RequestParam("fecha_salida") String fechaSalida,
            @RequestParam("fecha_retorno") String fechaRetorno,
            @RequestParam("comprador") String nombreComprador,
            @RequestParam("cantidad") int cantidad, Model model, HttpSession session) {

		List<ciudad> lista_ciudades = ciudadRepository.findAll();
		
        @SuppressWarnings("unchecked")
		List<detalle_venta> lista_detalle = (List<detalle_venta>) session.getAttribute("lista_detalle");
        if (lista_detalle == null) {
            lista_detalle = new ArrayList<>();
        }
        
		ciudad ciudad_origen = ciudadRepository.findById(origen).orElse(null);
		ciudad ciudad_destino = ciudadRepository.findById(destino).orElse(null);

		LocalDate fecha_salida = LocalDate.parse(fechaSalida);
		LocalDate fecha_retorno = LocalDate.parse(fechaRetorno);


		Date fecha_Salida =  Date.valueOf(fecha_salida);
		Date fecha_Retorno =  Date.valueOf(fecha_retorno);
		
		double precioBoleto = 50;
		
		detalle_venta detalleVenta = new detalle_venta();
		detalleVenta.setCodigo_postal_origen(ciudad_origen);
		detalleVenta.setCodigo_postal_destino(ciudad_destino);
		detalleVenta.setFecha_viaje(fecha_Salida);
		detalleVenta.setFecha_retorno(fecha_Retorno);
		detalleVenta.setCantidad(cantidad);
		detalleVenta.setSub_total(precioBoleto*cantidad);
		System.out.println(detalleVenta);
		lista_detalle.add(detalleVenta);
		
		Double monto_total = 0.0;
		for(detalle_venta detalle : lista_detalle) {
			monto_total +=detalle.getSub_total();
		}
		
		LocalDate localDate = LocalDate.now();
		Date date = Date.valueOf(localDate);
		
		venta vent = new venta();
		vent.setNombre_comprador(nombreComprador);
		vent.setFecha_venta(date);
		vent.setMonto_total(monto_total);

		lista_detalle.forEach(detalle -> detalle.setId_venta(vent));
		
		session.setAttribute("venta", vent);
		session.setAttribute("lista_detalle", lista_detalle);
		model.addAttribute("lista_detalle", lista_detalle);
		model.addAttribute("lista_ciudades", lista_ciudades);
		model.addAttribute("vent",vent);
		return "index";
	}
	
	@GetMapping("/comprar")
	public String comprarBoleto(Model model, HttpSession session) {
	    @SuppressWarnings("unchecked")
		List<detalle_venta> lista_detalle = (List<detalle_venta>) session.getAttribute("lista_detalle");
	    venta vent = (venta) session.getAttribute("venta");
	    
	    if(lista_detalle != null && !lista_detalle.isEmpty()) {
	        ventaRepository.save(vent);
	        
	        lista_detalle.forEach(e -> {
	            e.setId_venta(vent);
	            detalleRepository.save(e);
	        });
	        
	        lista_detalle = new ArrayList<>();
	        venta venta_nueva = new venta();
	        
	        session.setAttribute("venta", venta_nueva);
	        session.setAttribute("lista_detalles", lista_detalle);
	        return "mensaje";
	    }
	    return "redirect:/venta";
	}
	
	
}
