package ro.msg.learning.shop.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dtos.RevenueDTO;
import ro.msg.learning.shop.entitites.Revenue;
import ro.msg.learning.shop.mappers.RevenueMapper;
import ro.msg.learning.shop.services.interfaces.RevenueService;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/revenues")
public class RevenueController {
	@Autowired
	private final RevenueService revenueService;

	@GetMapping(value = "/{date}", produces = {"text/csv"})
	public ResponseEntity<List<RevenueDTO>> exportRevenuesByLocationId(@PathVariable LocalDate date, HttpServletResponse response) {

		List<Revenue> revenues = revenueService.findAllByDate(date);
		List<RevenueDTO> revenueDTOList = RevenueMapper.toDtoList(revenues);
		response.setHeader("Content-Disposition", "attachment; filename=revenues_for_"+date.toString()+".csv");

		return ResponseEntity.ok(revenueDTOList);
	}
}
