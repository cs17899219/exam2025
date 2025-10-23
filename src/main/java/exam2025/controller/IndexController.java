package exam2025.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import exam2025.common.controller.ResultData;
import exam2025.config.ConfigProperties;
import exam2025.dto.UserPointsTransferDTO;
import exam2025.vo.UserPointsTransferVO;


@RestController
public class IndexController {
    @Autowired
    ConfigProperties configProperties;

    @GetMapping("/")
    public String index() {
        return "UP";
    }

    @GetMapping("/wrong")
    public int wrong() {
        return 9 / 0;
    }

    @PostMapping("/api/points/transfer")
    public ResultData<UserPointsTransferVO> transfer(@RequestBody UserPointsTransferDTO dto) {
        // TODO
        return ResultData.success(null);
    }

}
