for f in *.png; do name=$(echo $f| cut -d'.' -f 1); cp ../Lion/caged.json "$name.json" ; done

for f in *.png
do
name=`echo ${f:0:-4}`
echo $name
display "$name.png"
gedit "$name.json"
done
