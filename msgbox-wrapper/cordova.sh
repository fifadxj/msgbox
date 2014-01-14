PATH=$PATH:/Users/cbsd/tools:/Volumes/work/software/adt-bundle-mac-x86_64-20131030/sdk/tools:/Volumes/work/software/adt-bundle-mac-x86_64-20131030/sdk/platform-tools
export PATH

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

cp platforms/android/bin/msgbox-debug.apk ../msgbox.apk

rm -r ../msgbox-cordova