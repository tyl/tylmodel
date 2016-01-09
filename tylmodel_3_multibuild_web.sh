kill -9 $(ps aux | grep '[j]ekyll' | awk '{print $2}')
clear

echo "Building Tylmodel Writers website..."
jekyll build --config configs/tylmodel/config_english.yml
# jekyll serve --config configs/doc/config_writers.yml
echo "done"

echo "Building Tylmodel Designers websote..."
jekyll build --config configs/tylmodel/config_italian.yml
# jekyll serve --config configs/doc/config_designers.yml
echo "done"

echo "All finished building all the web outputs!!!"
echo "Now push the builds to the server with . tylmodel_4_publish.sh"

