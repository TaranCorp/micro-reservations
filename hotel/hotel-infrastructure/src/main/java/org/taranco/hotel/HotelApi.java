package org.taranco.hotel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.taranco.hotel.dto.CreatedHotelResponse;
import org.taranco.hotel.dto.HotelCreateCommand;
import org.taranco.hotel.port.input.HotelApplicationService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/hotel")
class HotelApi {
    private final HotelApplicationService hotelApplicationService;

    HotelApi(HotelApplicationService hotelApplicationService) {
        this.hotelApplicationService = hotelApplicationService;
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    CreatedHotelResponse createHotel(@RequestBody HotelCreateCommand hotelCreateCommand) {
        return hotelApplicationService.initHotel(hotelCreateCommand.getName(), hotelCreateCommand.getRoomCreateCommands());
    }
}
