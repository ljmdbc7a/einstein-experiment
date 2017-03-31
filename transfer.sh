name=$1
version=$2
mvn clean
git init
echo -e ".idea\n*.iml\ntransfer.sh\n*.bak\n.svn\nlogs/\ntarget/" > .gitignore
#git remote add origin https://code.vdian.net/pay/$name.git

