package org.example.tapville.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.example.tapville.models.DiscountCard;
import org.example.tapville.models.StampCard;
import org.example.tapville.repositories.contracts.DiscountCardRepository;
import org.example.tapville.repositories.contracts.StampCardRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
@Component
public class QrCode {

    public void GenerateQrStamp(StampCard stampCard, StampCardRepository stampCardRepository) throws WriterException, IOException {
        String path = "D:/TapVille/TapVille/QrCodes/Stamp";

        BitMatrix matrix = new MultiFormatWriter().encode(
                stampCard.getId().toString(),
                BarcodeFormat.QR_CODE,
                500,
                500);

        String fileName = stampCard.getId().toString() + ".jpg";
        Path qrCodePath = Paths.get(path, fileName);
        MatrixToImageWriter.writeToPath(matrix,"jpg", qrCodePath);
        stampCard.setPath(qrCodePath.toString());

        stampCardRepository.save(stampCard);

        System.out.println("QR was created");
    }
    public void GenerateQrDiscount(DiscountCard discountCard, DiscountCardRepository discountCardRepository) throws WriterException, IOException {
        String path = "D:/TapVille/TapVille/QrCodes/Discount";

        BitMatrix matrix = new MultiFormatWriter().encode(
                discountCard.getId().toString(),
                BarcodeFormat.QR_CODE,
                500,
                500);

        String fileName = discountCard.getId().toString() + ".jpg";
        Path qrCodePath = Paths.get(path, fileName);
        MatrixToImageWriter.writeToPath(matrix,"jpg", qrCodePath);
        discountCard.setPath(qrCodePath.toString());

        discountCardRepository.save(discountCard);

        System.out.println("QR was created");
    }

}
