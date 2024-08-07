import java.net.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {

		try {

			// 書き込みファイル名
			String filename = "syain.csv";
			// テキストで書き込み為の準備( SHIFT_JIS )
			BufferedWriter bw = new BufferedWriter( new OutputStreamWriter( new FileOutputStream(filename), "MS932" ) );			

			// URL文字列
			String str = "https://lightbox.sakura.ne.jp/demo/json/syain_csv.php";
			// ターゲット
			URL url = new URL( str );
			// 接続オブジェクト
			HttpURLConnection http = (HttpURLConnection)url.openConnection();
			// GET メソッド 
			http.setRequestMethod("GET");
			// 接続 
			http.connect();
			
			// SHIFT_JIS でリーダーを作成
			InputStreamReader isr = new InputStreamReader(http.getInputStream(), "MS932" );   
			// 行単位で読み込む為の準備   
			BufferedReader br = new BufferedReader(isr);   
			// 読込み用
			String line_buffer;   
			// BufferedReader は、readLine が null を返すと読み込み終了   
			while ( null != (line_buffer = br.readLine() ) ) {   
				// 書き込み
				bw.write( line_buffer + "\r\n" );
			}

			// 閉じる   
			br.close();
			isr.close();
			http.disconnect();
			bw.close();

		}
		catch( Exception e ) {
			System.out.println( e.getMessage() );
		}
	}
}
