## ディレクトリ構成について
- ver4.xlsm  
  MicrosoftOffice2007以降のバージョンに対応したMicrosoftOfficeExcel32ビット版の作業報告書及び交通費精算書  
  64ビットOSマシンに標準にて入っているバージョン  
  （Mac版MicrosoftOfficeは除く）  

- ver4.xlsm.build01  
  MicrosoftOffice2007以降のバージョンに対応したMicrosoftOfficeExcel64ビット版の作業報告書及び交通費精算書  
  64ビットOSマシンに必ず入っているのではなく、Officeインストール時に意図的に選択しなければインストール不可能  
  Mac版MicrosoftOfficeは64ビット版が標準にてインストールされ、32ビット版のインストールは不可能  
  ３２ビット版にて用意されているMicrosoftWindowsCommonControl6.0が廃止されたため、交通費精算書にて利用しているユーザーフォームにファイルのドラッグ＆ドロップする機能が利用できないため急遽用意  

- ver4.xls  
  MicrosoftOffice2003以前のバージョンに対応したMicrosoftOfficeExcelの作業報告書及び交通費精算書  
  MicrosoftOfficeは持っているが2007より前のバージョンである人のために急遽用意
## 解消不可の問題点について
- ファイル名が文字化け  
  WindowsからMac、MacからWindowsにて日本語名ファイルをやり取りすることで発生  
  これはWindowsがShift-JIS、MacがUnicodeであることが原因  
  Mac版Office内ではShift-JISにて保存を行なっているため、ファイル名は文字化けが起きるがファイルの中身自体に文字化けは発生しない  

- ファイル内が文字化け  
  MicrosoftOfficeが提供するExcelを使用しているのかがそもそもの疑問  
  WPSOffice(旧：KingsoftOffice)を標準搭載した格安Windowsマシンが普及していること、iWorkを標準搭載したMacマシンが普及していることが挙げられる  
  社内システムを構築しオンライン上にてブラウザによる統一された入出力環境を提供することで解決可能
