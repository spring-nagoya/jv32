package myServlet;
 
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

 
@WebServlet (urlPatterns = {"/r07"})
public class R07Servlet
	extends HttpServlet
{
	@Override
	protected void doGet(
			HttpServletRequest req,
			HttpServletResponse resp)
	throws ServletException, IOException {

		funcServlet(req, resp, "get");

	}

	@Override
	protected void doPost(
			HttpServletRequest req,
			HttpServletResponse resp)
	throws ServletException, IOException {
	
		funcServlet(req, resp, "post");
	
	}

	protected void funcServlet(
			HttpServletRequest req,
			HttpServletResponse resp,
			String strKind)
	throws ServletException, IOException {

		//PDF出力
		
		//PDFの文章オブジェクトの生成
		// Documentクラス(iText)
		Document doc = new Document(PageSize.A4, 50, 50, 50, 50);
		
		//出力用のStream生成
		//  ByteArrayOutputStream:ブラウザ上
		//  FileOutputStream:ファイル出力
		ByteArrayOutputStream byteout = new ByteArrayOutputStream();
		
		//PDF出力を行うためのオブジェクト生成
		try {
			PdfWriter pdfwriter = PdfWriter.getInstance(doc, byteout);
			
			//フォント定義
			//BaseFont:表示用の基底フォント
			//Font:基底フォントを元にサイズ、色
			/* フォント設定部 */
			//(ゴシック15pt(太字)
			Font font_header = new Font(BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED),15,Font.BOLD);
			//ゴシック11pt
			Font font_g11 = new Font(BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED),11);
			//ゴシック10pt
			Font font_g10 = new Font(BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED),10);
			//明朝10pt
			Font font_m10 = new Font(BaseFont.createFont("HeiseiMin-W3", "UniJIS-UCS2-HW-H",BaseFont.NOT_EMBEDDED),10);
			//ゴシック11pt(下線あり)
			Font font_underline_11 = new Font(BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED),11,Font.UNDERLINE);
			//ゴシック11pt(赤)
			Font font_red_11 = new Font(BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED),11);
			font_red_11.setColor(255,0,0);
			//空白セル用フォント(非表示)
			Font font_empty = new Font(BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED),9);
			font_empty.setColor(255,255,255);
			
			//ヘッダーとフッターの出力(verで書き方が違う)
			pdfwriter.setPageEvent(new PdfPageEventHelper() {
				@Override
				public void onEndPage(PdfWriter writer, Document document) {
					// TODO 自動生成されたメソッド・スタブ
					super.onEndPage(writer, document);
					
					//PdfContentByte
					PdfContentByte pdfcb = writer.getDirectContent();
					
					//描画しているPDFの保存
					pdfcb.saveState();
					
					//ヘッダーとフッターの操作(11/19)
					
					//テキストの出力開始
					pdfcb.beginText();
					
					//フォントの定義(BaseFont)
					BaseFont font_head = null;
					try {
						font_head = BaseFont.createFont("HeiseiKakuGo-W5","UniJIS-UCS2-H",BaseFont.NOT_EMBEDDED);
					} catch (DocumentException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					} catch (IOException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
					
					//fontの設定
					pdfcb.setFontAndSize(font_head, 10);
					
					
					//フッター出力 例：- 1 -
					String strPageNo = " - "+writer.getPageNumber()+" - ";
					
					//フッターの設定
					pdfcb.showTextAligned
						(PdfContentByte.ALIGN_CENTER, //寄せ
						 strPageNo, 
						 (document.left()+document.right())/2, //x座標
						 document.bottom(-30), //y座標
						 0);//回転
					
					
					//テキストの出力終了
					pdfcb.endText();
					
					//再保存
					pdfcb.restoreState();
				}
			});
			
			//PDF文章の出力の開始
			doc.open();
			
			//単純な文章の出力
			doc.add( new Paragraph("初めてのPDF", font_g11) );
			
			//装飾付きの文字列出力
			Paragraph para_1 = new Paragraph("レクまで後10日",font_red_11);
			para_1.setAlignment(Element.ALIGN_CENTER);
			doc.add(para_1);
			
			//新しいページの追加
			doc.newPage();
			
			doc.add(new Paragraph("表形式の出力", font_g11));
			
			//表形式
			//  PdfPTable
			//  PdfPCell
			PdfPTable table = new PdfPTable(3);
			//          ↑列数3
			
			
			
		} catch (DocumentException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} finally {
			//PDF文章の出力の終了
			doc.close();
		}
		

		//PDFデータの送信
		resp.setContentType("application/pdf");
		resp.setContentLength(byteout.size());
		OutputStream out = resp.getOutputStream();
		out.write(byteout.toByteArray());
		out.close();
	}

}