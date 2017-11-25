package pds.esibank.dataaccess.services;

import org.springframework.stereotype.Service;
import pds.esibank.dataaccess.entities.Share;
import pds.esibank.dataaccess.repositories.ShareRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShareService implements ShareRepository {

    @Resource
    private ShareRepository shareRepository;

    @Override
    public List<Share> findAll() {
        return shareRepository.findAll();
    }

    @Override
    public Share findByName(String name) {
        return shareRepository.findByName(name);
    }

    @Override
    public void save(Share share) {
        shareRepository.save(share);
    }

    @Override
    public void update(Share share) {
        shareRepository.update(share);
    }

}
