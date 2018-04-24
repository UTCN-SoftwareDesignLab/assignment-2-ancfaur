package bookstoreApp.service.report;

import bookstoreApp.entity.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Component
public class PdfFormater implements Formater {
    @Override
    public void formatBooks(List<Book> books) throws IOException {
        PDDocument document = new PDDocument();
        PDPageContentStream contentStream=null;
        int bookNo=0;

        for (Book book : books) {
            if (bookNo%6==0){
                if (contentStream!=null){
                    finishCurrentContentStream(contentStream);
                }
                contentStream=intializeNewContentStream(document);
            }
            contentStream.showText("id ="+ book.getId());
            contentStream.showText("isbn ="+ book.getIsbn());
            contentStream.newLine();
            contentStream.showText("title ="+book.getName());
            contentStream.newLine();
            contentStream.showText("price ="+new Float (book.getPrice()).toString());
            contentStream.newLine();
            contentStream.showText("quantity ="+new Integer(book.getQuantity()).toString());
            contentStream.newLine();
            contentStream.showText("author id="+book.getAuthor().getId());
            contentStream.newLine();
            contentStream.showText("author name="+book.getAuthor().getName());
            contentStream.newLine();
            contentStream.newLine();
            bookNo++;
        }
        finishCurrentContentStream(contentStream);

        Date date = new Date();
        String fileName =date.toString().replace(":", " ");
        document.save("D:\\Anca\\manaBionica\\an3\\sem2\\softwareDesign\\assignments\\assignment-2-ancfaur\\Bookstore\\reports\\"+fileName+ ".pdf");
        document.close();
    }

    private PDPageContentStream intializeNewContentStream(PDDocument document) throws IOException {
        PDPage page = new PDPage();
        document.addPage(page);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.beginText();
        contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(25, 725);
        return contentStream;
    }

    private void finishCurrentContentStream(PDPageContentStream contentStream) throws IOException {
        contentStream.endText();
        contentStream.close();
    }



}
