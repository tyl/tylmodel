echo 'Killing all Jekyll instances'
kill -9 $(ps aux | grep '[j]ekyll' | awk '{print $2}')
clear


echo "Building PDF-friendly HTML site for Tylmodel English ..."
jekyll serve --detach --config configs/tylmodel/config_english.yml,configs/tylmodel/config_english_pdf.yml
echo "done"

echo "Building PDF-friendly HTML site for Tylmodel Italian ..."
jekyll serve --detach --config configs/tylmodel/config_italian.yml,configs/tylmodel/config_italian_pdf.yml
echo "done"

echo "All done serving up the PDF-friendly sites. Now let's generate the PDF files from these sites."
echo "Now run . tylmodel_2_multibuild_pdf.sh"
