# Doc Writers
echo "Building the Tylmodel English PDF ..."
prince --javascript --input-list=../doc_outputs/tylmodel/english-pdf/prince-file-list.txt -o tylmodel/files/tylmodel_english_pdf.pdf;
echo "done"

# Doc Designers
echo "Building Tylmodel Italian PDF ..."
prince --javascript --input-list=../doc_outputs/tylmodel/italian-pdf/prince-file-list.txt -o tylmodel/files/tylmodel_italian_pdf.pdf;
echo "done"

echo "All done building the PDFs!"
echo "Now build the web outputs: . tylmodel_3_multibuild_web.sh"
