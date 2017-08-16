package example

import geb.spock.GebReportingSpec

import groovy.sql.Sql

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared

@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BaseGebSpec extends GebReportingSpec {
	@Value('${local.server.port}')
	int port

	// connect to db
	@Shared def sql = Sql.newInstance("jdbc:mysql://localhost:3306/example_test", "root", "", "com.mysql.jdbc.Driver")

	def setup() {
		println('Set up.')
		browser.setBaseUrl("http://localhost:${port}")
		sql.execute "delete from greeting_message;"
	}

	def cleanup() {
		println('Clean up!')
	}

	def cleanupSpec() {
		println('Clean up Spec!')
		sql.close()
	}
}