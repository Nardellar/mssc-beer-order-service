package guru.sfg.beer.order.service.web.mappers;

import guru.sfg.beer.order.service.domain.BeerOrderLine;
import guru.sfg.beer.order.service.services.MSSCbeerService.MSSCBeerServiceService;
import guru.sfg.beer.order.service.services.MSSCbeerService.model.BeerDto;
import guru.sfg.beer.order.service.web.model.BeerOrderLineDto;

public class BeerOrderLineMapperDecorator implements BeerOrderLineMapper{

    private MSSCBeerServiceService mSSCBeerServiceService;
    private BeerOrderLineMapper mapper;

    @Override
    public BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line) {
        BeerOrderLineDto dto = mapper.beerOrderLineToDto(line);
        BeerDto beerDto = mSSCBeerServiceService.getBeerByUpc(dto.getUpc()).orElseThrow(() -> new RuntimeException("Beer not found"));
        dto.setBeerName(beerDto.getBeerName());
        dto.setBeerStyle(beerDto.getBeerStyle());
        dto.setPrice(beerDto.getPrice());
        return dto;
    }

    @Override
    public BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto) {
        return mapper.dtoToBeerOrderLine(dto);
    }


}
