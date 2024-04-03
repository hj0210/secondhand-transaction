package secondhandtransaction.infra;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import secondhandtransaction.domain.*;

//<<< Clean Arch / Inbound Adaptor

@RestController
// @RequestMapping(value="/reserves")
@Transactional
public class ReserveController {

    @Autowired
    ReserveRepository reserveRepository;

    @RequestMapping(
        value = "reserves/{id}/reservecancel",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8"
    )
    public Reserve reserveCancel(
        @PathVariable(value = "id") Long id,
        HttpServletRequest request,
        HttpServletResponse response
    ) throws Exception {
        System.out.println("##### /reserve/reserveCancel  called #####");
        Optional<Reserve> optionalReserve = reserveRepository.findById(id);

        optionalReserve.orElseThrow(() -> new Exception("No Entity Found"));
        Reserve reserve = optionalReserve.get();
        reserve.reserveCancel();

        reserveRepository.save(reserve);
        return reserve;
    }
}
//>>> Clean Arch / Inbound Adaptor
