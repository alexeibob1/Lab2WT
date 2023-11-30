package by.bsuir.lab2.controller.command.impl.admin;

import by.bsuir.lab2.bean.Locale;
import by.bsuir.lab2.bean.ProductLocalInfo;
import by.bsuir.lab2.bean.dto.LocalizedProductTO;
import by.bsuir.lab2.controller.command.Command;
import by.bsuir.lab2.controller.constant.CommandName;
import by.bsuir.lab2.controller.constant.ViewPath;
import by.bsuir.lab2.controller.util.UrlUtil;
import by.bsuir.lab2.service.ProductService;
import by.bsuir.lab2.service.ServiceFactory;
import by.bsuir.lab2.service.exception.ServiceException;
import by.bsuir.lab2.service.exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AddDrugCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddDrugCommand.class);
    private static final String YES_OPTION = "yes";
    private static final String NO_OPTION = "no";
    private static final String AMOUNT_PARAM = "amount";
    private static final String PRICE_PARAM = "price";
    private static final String DOSAGE_PARAM = "dosage";
    
    private static final String NAME_RU_PARAM = "name_ru";
    private static final String NAME_EN_PARAM = "name_en";
    private static final String MANUFACTURER_RU_PARAM = "manufacturer_ru";
    private static final String MANUFACTURER_EN_PARAM = "manufacturer_en";
    private static final String FORM_RU_PARAM = "form_ru";
    private static final String FORM_EN_PARAM = "form_en";
    private static final String DOSAGE_UNIT_RU_PARAM = "dosageUnit_ru";
    private static final String DOSAGE_UNIT_EN_PARAM = "dosageUnit_en";
    private static final String PRESCRIPTION_PARAM = "prescriptionOption";
    
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "";
        try {
            LocalizedProductTO localizedProductTO = formLocalizedProduct(request);
            ProductService productService = ServiceFactory.getInstance().getProductService();
            productService.addProduct(localizedProductTO);
            //viewPath = request.getContextPath() + CommandName.SHOW_DRUGS_COMMAND;
            viewPath = UrlUtil.getRefererPage(request);
        } catch (ValidationException e) {
            LOGGER.warn("Invalid drug data, validation failed", e);
            //create message stored in session
        } catch (ServiceException e) {
            LOGGER.error("Exception during adding new drug.", e);
            viewPath = request.getContextPath() + CommandName.GO_TO_ERROR_503_COMMAND;
        }
        response.sendRedirect(viewPath);
    }
    
    private LocalizedProductTO formLocalizedProduct(HttpServletRequest request) throws ValidationException {
        LocalizedProductTO localizedProduct = new LocalizedProductTO();
        String isNeedPrescription = request.getParameter(PRESCRIPTION_PARAM);
        if (YES_OPTION.equals(isNeedPrescription)) {
            localizedProduct.setIsNeedPrescription(true);
        } else if (NO_OPTION.equals(isNeedPrescription)) {
            localizedProduct.setIsNeedPrescription(false);
        }
        
        try {
            String amount = request.getParameter(AMOUNT_PARAM);
            localizedProduct.setAmount(Integer.parseInt(amount));
            String price = request.getParameter(PRICE_PARAM);
            localizedProduct.setPrice(new BigDecimal(price));
        } catch (NumberFormatException e) {
            throw new ValidationException(e);
        }
        
        List<ProductLocalInfo> productLocalInfoList = new ArrayList<>();
        ProductLocalInfo productLocalInfoRu = new ProductLocalInfo();
        ProductLocalInfo productLocalInfoEn = new ProductLocalInfo();
        
        productLocalInfoRu.setLocale(Locale.RU);
        productLocalInfoEn.setLocale(Locale.EN);

        productLocalInfoRu.setName(request.getParameter(NAME_RU_PARAM));
        productLocalInfoEn.setName(request.getParameter(NAME_EN_PARAM));

        productLocalInfoRu.setManufacturer(request.getParameter(MANUFACTURER_RU_PARAM));
        productLocalInfoEn.setManufacturer(request.getParameter(MANUFACTURER_EN_PARAM));

        productLocalInfoRu.setDrugForm(request.getParameter(FORM_RU_PARAM));
        productLocalInfoEn.setDrugForm(request.getParameter(FORM_EN_PARAM));

        productLocalInfoRu.setDosageUnit(request.getParameter(DOSAGE_UNIT_RU_PARAM));
        productLocalInfoEn.setDosageUnit(request.getParameter(DOSAGE_UNIT_EN_PARAM));
        
        
        productLocalInfoList.add(productLocalInfoRu);
        productLocalInfoList.add(productLocalInfoEn);
        localizedProduct.setProductLocalInfoList(productLocalInfoList);
        return localizedProduct;
    }
}
