// swift-tools-version:5.9
import PackageDescription

// This Package.swift is auto-generated during release.
// Do not edit manually - it will be overwritten by CI.
//
// To use this package, add it to your Xcode project:
// File > Add Package Dependencies > Enter repository URL:
// https://github.com/samuolis/timeago-kmp

let package = Package(
    name: "TimeAgoKMP",
    platforms: [
        .iOS(.v14),
        .macOS(.v12)
    ],
    products: [
        .library(
            name: "TimeAgoKMP",
            targets: ["TimeAgoKMP"]
        )
    ],
    targets: [
        // Binary target will be added after first release
        // .binaryTarget(
        //     name: "TimeAgoKMP",
        //     url: "https://github.com/samuolis/timeago-kmp/releases/download/v0.1.0/TimeAgoKMP.xcframework.zip",
        //     checksum: "<checksum>"
        // )
    ]
)
