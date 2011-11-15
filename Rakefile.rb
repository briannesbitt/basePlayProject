require 'fileutils'

PLAY_PATH="z:/dev/playframework/"
PROJECT="BasePlayProject"
SRC_APP_PATH="app/"
SRC_MAIN_PATH="src/"
SRC_TEST_PATH="test/"
SRC_MAIN_RESOURCES_PATH="lib/"
SRC_TEST_RESOURCES_PATH="lib/"
OUT_MAIN_PATH="tmp/classes/"
OUT_TEST_PATH="tmp/classes/"
JAR="lib/play-#{PROJECT}.jar"
TEST_PACKAGES="test."

def cp(sep = nil)
   sep ||= File::PATH_SEPARATOR
   ret = FileList[PLAY_PATH+'framework/classes',PLAY_PATH+'framework/lib/*.jar',PLAY_PATH+'framework/*.jar',SRC_MAIN_RESOURCES_PATH+'/**/*.jar'].join(sep)
end

def cp_test(sep = nil)
   sep ||= File::PATH_SEPARATOR
   ret = FileList[PLAY_PATH+'framework/classes',PLAY_PATH+'framework/lib/*.jar',PLAY_PATH+'framework/*.jar',SRC_MAIN_RESOURCES_PATH+'/**/*.jar',SRC_TEST_RESOURCES_PATH+'/**/*.jar', JAR].join(sep)
end

def src(sep = nil)
   sep ||= " "
   ret = FileList[SRC_MAIN_PATH+'/**/*.java',SRC_APP_PATH+'/**/*.java'].join(sep)
end

def src_test(sep = nil)
   sep ||= " "
   ret = FileList[SRC_TEST_PATH+'/**/*.java'].join(sep)
end

desc "Clean deletes all intermediate and final build items"
task :clean do
   remove_dir "tmp", :force => true
end

desc "Compiles #{SRC_MAIN_PATH} to #{OUT_MAIN_PATH}"
task :compilemain => :clean do
   mkdir_p OUT_MAIN_PATH
   sh "javac -g -d #{OUT_MAIN_PATH} -cp #{cp} #{src}"
end

desc "Compiles #{SRC_TEST_PATH} to #{OUT_TEST_PATH}"
task :compiletest => [:clean, :compilemain, :build] do
   mkdir_p OUT_TEST_PATH
   sh "javac -g -d #{OUT_TEST_PATH} -cp #{cp_test} #{src_test}"
end

desc "Compile all java src"
task :compile => [:compilemain, :compiletest]

desc "Creates #{JAR}"
task :build => :compilemain do
   puts SRC_MAIN_PATH+"play.plugins"
   puts OUT_MAIN_PATH
   FileUtils.cp SRC_MAIN_PATH+"play.plugins", OUT_MAIN_PATH
   sh "jar cvf #{JAR} -C #{OUT_MAIN_PATH} . > NUL"
end

desc "Runs tests in all classes Test*"
task :test, :should_compile do

   should_compile ||= true

   Rake::Task[:compile].invoke unless !should_compile

   sep ||= File::PATH_SEPARATOR

   puts ""
   sh "java -cp #{cp_test};#{OUT_TEST_PATH} com.nesbot.commons.tests.DebugRunner #{TEST_PACKAGES}" do |ok, res|
      if ! ok
         fail "***** Uh no... Test failed!!"
      end
   end
   puts ""
end

task :default => :compile