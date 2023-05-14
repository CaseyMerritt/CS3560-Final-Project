package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import database.HibernateSessionFactory;

@Entity
@Table(name = "films", schema = "library")

public class Film extends Item
{
	@Column(name = "length")
	private Integer length; // length is measured in minutes
	@Column(name = "release_date")
	private Date releaseDate;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "director_id")
	private Director director;
	
	public Film() {}
	
	public Film(String title, String description, String location, double dailyPrice)
	{
		super(title, description, location, dailyPrice);
	}

	// setters
	public void setLength(int length)
	{
		this.length = length;
	}

	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public void setDirector(Director director)
	{
		this.director = director;
	}
	
	// getters
	public int getLength()
	{
		return length == null ? 0 : length;
	}

	public Date getReleaseDate()
	{
		return releaseDate;
	}

	public Director getDirector()
	{
		return director;
	}
	
	@Override
	public void delete() {
		this.director = null;
		update();
		super.delete();
	}
	
	// overriding toString()
	@Override
	public String toString()
	{
		return director + "Film length: " + length + " minutes\nRelease date: " +
			   releaseDate + super.toString();
	}
	
	public static List<Film> findBy(FilmQuery filmQuery) {
		SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Film> query = cb.createQuery(Film.class);
		Root<Film> root = query.from(Film.class);
		Join<Film, Director> joined = root.join("director", JoinType.LEFT);
		query.distinct(true);
		
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if (filmQuery.getCode() != null) {
			predicates.add(cb.equal(root.get("code"), filmQuery.getCode()));
		}
		
		if (filmQuery.getTitle() != null) {
			predicates.add(cb.like(root.get("title"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "title"), "%")
			)));
		}
		
		if (filmQuery.getLocation() != null) {
			predicates.add(cb.like(root.get("location"), cb.concat("%", 
					cb.concat(cb.parameter(String.class, "location"), "%")
			)));
		}
		
		if (filmQuery.getMaxDailyPrice() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("dailyPrice"), filmQuery.getMaxDailyPrice()));
		}
		
		if (filmQuery.getMinDailyPrice() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("dailyPrice"), filmQuery.getMinDailyPrice()));
		}
		
		if (filmQuery.isOnlyAvailable()) {
			predicates.add(cb.equal(root.get("available"), true));
		}
		
		if (filmQuery.getMaxLength() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("length"), filmQuery.getMaxLength()));
		}
		
		if (filmQuery.getMinLength() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("length"), filmQuery.getMinLength()));
		}
		
		if (filmQuery.getReleasedAfter() != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get("releaseDate"), filmQuery.getReleasedAfter()));
		}
		
		if (filmQuery.getReleasedBefore() != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get("releaseDate"), filmQuery.getReleasedBefore()));
		}
		
		if (filmQuery.getDirectorName() != null) {
			predicates.add(cb.like(joined.get("name"),cb.concat("%", 
					cb.concat(cb.parameter(String.class, "directorName"), "%")
			)));
		}
		
		if (predicates.size() > 0) 
			query = query.where(cb.and(predicates.toArray(new Predicate[0])));
		
		TypedQuery<Film> typedQuery = session.createQuery(query);
		
		if (filmQuery.getTitle() != null) {
			typedQuery.setParameter("title", filmQuery.getTitle());
		}
		
		if (filmQuery.getLocation() != null) {
			typedQuery.setParameter("location", filmQuery.getLocation());
		}
		
		if (filmQuery.getDirectorName() != null) {
			typedQuery.setParameter("directorName", filmQuery.getDirectorName());
		}
		
		List<Film> films = typedQuery.getResultList();
		
		session.getTransaction().commit();
		
		return films;
	}
}
