package guru.sfg.beer.order.service.services.MSSCbeerService;

import guru.sfg.beer.order.service.services.MSSCbeerService.model.BeerDto;
import guru.sfg.beer.order.service.web.mappers.BeerOrderLineMapperDecorator;
import guru.sfg.beer.order.service.web.mappers.DateMapper;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.Optional;
import java.util.UUID;

public interface MSSCBeerServiceService {

    Optional <BeerDto> getBeerById(UUID beerId);

    Optional<BeerDto> getBeerByUpc(String upc);
}
