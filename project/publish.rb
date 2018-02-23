#!/usr/bin/env ruby

def exec(cmd)
  abort("Error encountered, aborting") unless system(cmd)
end

puts "CI=#{ENV['CI']}"
puts "TRAVIS_BRANCH=#{ENV['TRAVIS_BRANCH']}"
puts "TRAVIS_PULL_REQUEST=#{ENV['TRAVIS_PULL_REQUEST']}"
puts "PUBLISH=#{ENV['PUBLISH']}"
puts

unless ENV['CI'] == 'true'
  abort("ERROR: Not running on top of Travis, aborting!")
end

unless ENV['PUBLISH'] == 'true'
  puts "Publish is disabled"
  exit
end

branch = ENV['TRAVIS_BRANCH']
version = nil

unless branch =~ /^v(\d+\.\d+\.\d+)$/ ||
  (branch == "snapshot" && ENV['TRAVIS_PULL_REQUEST'] == 'false')

  puts "Only triggering deployment on the `snapshot` branch, or for version tags " +
       "and not for pull requests or other branches, exiting!"
  exit 0
else
  version = $1
  puts "Version branch detected: #{version}" if version
end

# Forcing a change to the root directory, if not there already
Dir.chdir(File.absolute_path(File.join(File.dirname(__FILE__), "..")))

# Go, go, go
exec("sbt release")
