package com.example.demo.microservicessample.controller;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.microservicessample.service.MicroServicesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value = "TriangleTypes")
public class TriangleTypeController {
	@Autowired
	private MicroServicesService microServicesService;
	// Logger to log details
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * Given three sides return triangle type
	 * 
	 * @param sideDimension1
	 * @param sideDimension2
	 * @param sideDimension3
	 * @return Response entity with type of triangle
	 * @throws Exception 
	 */
	@ApiOperation(value = "Finds triangle type from its sides", notes = "Operation to find triangle type given its sides")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "Invalid Input: Please enter a number as input") })
	@RequestMapping(value = "/TriangleType", method = RequestMethod.GET)
	public ResponseEntity<Object> fetchTriangleType(@RequestParam("a") Object a, @RequestParam("b") Object b,
			@RequestParam("c") Object c) throws NumberFormatException {
		try {
			logger.debug("fetchTriangleType: API called: /TriangleType");
			Integer sideDimension1 = new Integer(a.toString());
			Integer sideDimension2 = new Integer(b.toString());
			Integer sideDimension3 = new Integer(c.toString());
			if (sideDimension1 <= 0 || sideDimension2 <= 0 || sideDimension3 <= 0) {
				logger.error("Length of sides cannot be equal to or less than zero");
				throw new InvalidParameterException("Length of sides cannot be equal to or less than zero"); //"Length of sides cannot be equal to or less than zero";
			}

			if (sideDimension1 > sideDimension2 + sideDimension3 || sideDimension2 > sideDimension1 + sideDimension3
					|| sideDimension3 > sideDimension1 + sideDimension2) {
				logger.error("Sum of any two sides must be larger than the remaining side");
				throw new InvalidParameterException("Sum of any two sides must be larger than the remaining side");
			}
			
			return ResponseEntity.status(HttpStatus.OK).cacheControl(CacheControl.noCache())
					.header("Pragma", "no-cache")
					.body(microServicesService.fetchTriangleType(sideDimension1, sideDimension2, sideDimension3));
		} catch (NumberFormatException numberFormatExcpetion) {
			logger.error("fetchTriangleType: Invalid input");
			Map<String, String> response = new HashMap<String, String>();
			response.put("error", "Invalid parameter value");
			return ResponseEntity.badRequest().body(response);
		}catch(InvalidParameterException ipe) {
			logger.error("marker, fetchTriangleType: Invalid input :: "+ipe.getMessage());
			Map<String, String> response = new HashMap<String, String>();
			response.put("error", ipe.getMessage());
			return ResponseEntity.badRequest().body(response);
		}

	}
}
