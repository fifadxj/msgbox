export PATH=$PATH:/workspace/tool/adt-bundle-mac-x86_64-20131030/sdk/platform-tools:/workspace/tool/adt-bundle-mac-x86_64-20131030/sdk/tools

cd ../msgbox-m
grunt wrapper-sit

cd ../msgbox-wrapper
rm -r msgbox-cordova
cordova create msgbox-cordova com.cangshudoudou.msgbox.wrapper msgbox

cd msgbox-cordova
rm -r www/css www/img www/js www/index.html
cp -R ../../msgbox-m/dist/* www

cordova platform add android

rm -r platforms/android/res/drawable*
mkdir platforms/android/res/drawable
cp www/img/logo.jpg platforms/android/res/drawable/icon.jpg

cordova build

cp platforms/android/ant-build/msgbox-debug.apk ../msgbox.apk

#rm -r ../msgbox-cordova