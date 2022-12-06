terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "4.40.0"
    }
  }
  backend "s3" {
    bucket = "analytics-1009"
    key    = "1009/demo.state"
    region = "eu-west-1"
  }
}